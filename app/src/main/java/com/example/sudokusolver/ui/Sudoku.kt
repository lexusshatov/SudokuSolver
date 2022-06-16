package com.example.sudokusolver.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sudokusolver.sudoku.Sudoku
import com.example.sudokusolver.sudoku.solution.squares

@Composable
fun Sudoku(sudoku: Sudoku) {
    Column {
        repeat(3) { rowIndex ->
            Row {
                repeat(3) { cellIndex ->
                    Square(sudoku.grid.squares[rowIndex * 3 + cellIndex])
                }
            }
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