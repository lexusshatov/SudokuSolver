package com.example.sudokusolver.sudoku

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class SudokuCalculator(private var sudoku: Sudoku) {

    private val candidates: List<MutableSet<Int>> =
        generateSequence { (1..9).toMutableSet() }.take(sudoku.grid.size).toList()

    private fun removeFilledCells() {
        sudoku.grid.forEachIndexed { index, element ->
            if (element != null) candidates[index].clear()
        }
    }

    private fun removeFromRows() {
        candidates.forEachIndexed { index, elementCandidates ->
            val row = sudoku.getRowByIndex(index)
            elementCandidates.removeIf { element -> row.contains(element) }
        }
    }

    private fun removeFromColumns() {
        candidates.forEachIndexed { index, elementCandidates ->
            val column = sudoku.getColumnByIndex(index)
            elementCandidates.removeIf { element -> column.contains(element) }
        }
    }

    private fun removeFromSquares() {
        candidates.forEachIndexed { index, elementCandidates ->
            val square = sudoku.getSquareByIndex(index)
            elementCandidates.removeIf { element -> square.contains(element) }
        }
    }

    fun calculate(): Sudoku {
        var isElementAdded: Boolean
        do {
            isElementAdded = false
            removeFilledCells()
            removeFromRows()
            removeFromColumns()
            removeFromSquares()
            candidates.forEachIndexed { index, mutableSet ->
                if (mutableSet.count() == 1) {
                    val grid = sudoku.grid.toMutableList().apply { set(index, mutableSet.first()) }
                    sudoku = Sudoku(grid.toTypedArray())
                    isElementAdded = true
                }
            }
        } while (isElementAdded)
        return sudoku
    }
}