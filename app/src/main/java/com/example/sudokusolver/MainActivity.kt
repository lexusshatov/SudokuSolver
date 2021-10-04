package com.example.sudokusolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sudokusolver.sudoku.SudokuGenerator
import com.example.sudokusolver.ui.theme.SudokuSolverTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var sudoku by remember { mutableStateOf(SudokuGenerator.generateSudoku()) }

            SudokuSolverTheme {
                Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)) {
                    LazyVerticalGrid(cells = GridCells.Fixed(9)) {
                        items(sudoku.grid) { number ->
                            Cell(number = number)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = {
                            sudoku = SudokuGenerator.generateSudoku()
                        }) {
                            Text(text = "Recreate")
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
            modifier = Modifier.fillMaxSize()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = number?.toString() ?: "")
            }
        }
    }

}