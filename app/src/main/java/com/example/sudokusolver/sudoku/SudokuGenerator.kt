package com.example.sudokusolver.sudoku

import com.example.sudokusolver.sudoku.solution.columns
import com.example.sudokusolver.sudoku.solution.rows
import kotlin.random.Random
import kotlin.random.nextInt

object SudokuGenerator {

    private var sudoku: Sudoku = Sudoku()

    private fun shuffleRows(first: Int, second: Int) {
        if (!sudoku.inSameGroup(first, second)) {
            throw IllegalArgumentException("Can't shuffle rows in different groups")
        }
        val rows = sudoku.grid.rows
        val firstRow = rows[first]
        val secondRow = rows[second]
        val mutableGrid = sudoku.grid.toMutableList()
        firstRow.forEachIndexed { index, element ->
            mutableGrid[second * 9 + index] = element
        }
        secondRow.forEachIndexed { index, element ->
            mutableGrid[first * 9 + index] = element
        }
        sudoku = Sudoku(mutableGrid)
    }

    private fun shuffleColumns(first: Int, second: Int) {
        if (!sudoku.inSameGroup(first, second)) {
            throw IllegalArgumentException("Can't shuffle columns in different groups")
        }
        val columns = sudoku.grid.columns
        val firstColumn = columns[first]
        val secondColumn = columns[second]
        val mutableGrid = sudoku.grid.toMutableList()
        firstColumn.forEachIndexed { index, element ->
            mutableGrid[index * 9 + second] = element
        }
        secondColumn.forEachIndexed { index, element ->
            mutableGrid[index * 9 + first] = element
        }
        sudoku = Sudoku(mutableGrid)
    }

    fun generateSudoku(difficult: Difficult = Difficult.NORMAL): Sudoku {
        randomSudoku()
        while (sudoku.grid.count { it != null } > difficult.gridSize) {
            deleteRandomNumber()
        }
        return sudoku.copy()
    }

    private fun randomSudoku() {
        sudoku = Sudoku()
        repeat(Random.nextInt(100..256)) {
            val group = Sudoku.groups.random()
            if (Random.nextBoolean()) {
                shuffleRows(group.random(), group.random())
            } else {
                shuffleColumns(group.random(), group.random())
            }
        }
    }

    private fun deleteRandomNumber() {
        val mutableGrid = sudoku.grid.toMutableList()
        var deleted = false
        while (!deleted) {
            val randomIndex = Random.nextInt(0..mutableGrid.lastIndex)
            if (mutableGrid[randomIndex] != null) {
                mutableGrid[randomIndex] = null
                deleted = true
            }
        }
        sudoku = Sudoku(mutableGrid)
    }

}