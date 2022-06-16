package com.example.sudokusolver.sudoku

import com.example.sudokusolver.sudoku.solution.columns
import com.example.sudokusolver.sudoku.solution.rows
import com.example.sudokusolver.sudoku.solution.squares

data class Sudoku(
    val grid: List<Int?> = listOf(
        9, 5, 1, 7, 3, 2, 4, 8, 6,
        8, 4, 7, 6, 9, 1, 3, 5, 2,
        2, 6, 3, 5, 8, 4, 9, 7, 1,

        4, 1, 5, 9, 2, 8, 7, 6, 3,
        3, 2, 6, 1, 7, 5, 8, 9, 4,
        7, 8, 9, 4, 6, 3, 2, 1, 5,

        6, 7, 2, 3, 1, 9, 5, 4, 8,
        5, 9, 8, 2, 4, 6, 1, 3, 7,
        1, 3, 4, 8, 5, 7, 6, 2, 9
    ),
) {

    init {
        if (!isCorrectGrid()) throw IllegalArgumentException("Wrong grid")
    }

    fun inSameGroup(first: Int, second: Int): Boolean {
        return groups.any { group -> first in group && second in group }
    }

    private fun isCorrectGrid(): Boolean {
        return grid.size == 81
                && grid.all { it in 1..9 || it == null }
                && grid.rows.all { it.filterNotNull().isSet() }
                && grid.columns.all { it.filterNotNull().isSet() }
                && grid.squares.all { it.filterNotNull().isSet() }
    }

    private fun <T> List<T>.isSet(): Boolean {
        return size == toSet().size
    }

    companion object {
        val groups = listOf(0..2, 3..5, 6..8)
    }

}
