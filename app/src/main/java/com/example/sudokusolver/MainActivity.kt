package com.example.sudokusolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sudokusolver.sudoku.Difficult
import com.example.sudokusolver.sudoku.Sudoku
import com.example.sudokusolver.sudoku.SudokuGenerator
import com.example.sudokusolver.sudoku.solution.SolveChain
import com.example.sudokusolver.ui.theme.SudokuSolverTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var difficult by remember { mutableStateOf(Difficult.EASY) }
            var sudoku by mutableStateOf(SudokuGenerator.generateSudoku(difficult))

            SudokuSolverTheme {
                Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)) {
                    Sudoku(sudoku = sudoku)
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            DifficultSlider(onValueChange = {
                                difficult = it
                            })
                            Text(text = difficult.toString())
                            Spacer(modifier = Modifier.height(15.dp))
                            Button(onClick = {
                                sudoku = SudokuGenerator.generateSudoku(difficult)
                            }) {
                                Text(text = "Generate")
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(onClick = {
                                sudoku = SolveChain.Base().solve(sudoku)
                            }) {
                                Text(text = "Calculate")
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun Cell(number: Int?) {
        Surface(
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier.requiredSize(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = number?.toString() ?: "")
            }
        }
    }

    @Composable
    private fun Square(cells: List<Int?>) {
        if (cells.size != 9) throw IllegalStateException("Wrong square size")
        Surface(
            modifier = Modifier.padding(1.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Column {
                repeat(3) { rowIndex ->
                    Row {
                        repeat(3) { cellIndex ->
                            Cell(cells[rowIndex * 3 + cellIndex])
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun Sudoku(sudoku: Sudoku) {
        val squares = (0..8).map { sudoku.getSquareByIndex(it) }
        Column {
            repeat(3) { rowIndex ->
                Row {
                    repeat(3) { cellIndex ->
                        Square(squares[rowIndex * 3 + cellIndex])
                    }
                }
            }
        }
    }

    @Composable
    private fun DifficultSlider(onValueChange: (Difficult) -> Unit) {
        val min = Difficult.values().minOf { it.ordinal }.toFloat()
        val max = Difficult.values().maxOf { it.ordinal }.toFloat()
        var value by remember { mutableStateOf(min) }
        val range = min..max

        Slider(
            modifier = Modifier.padding(horizontal = 5.dp),
            value = value,
            steps = 1,
            onValueChange = { difficult ->
                value = difficult
                Difficult.values().firstOrNull { it.ordinal == difficult.toInt() }
                    ?.let { onValueChange(it) }
            },
            valueRange = range
        )
    }
}