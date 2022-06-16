package com.example.sudokusolver.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sudokusolver.sudoku.Difficult
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