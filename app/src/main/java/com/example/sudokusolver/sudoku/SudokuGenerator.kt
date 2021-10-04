package com.example.sudokusolver.sudoku

import kotlin.random.Random
import kotlin.random.nextInt

object SudokuGenerator {

    private var sudoku: Sudoku = Sudoku()

    private var grid: List<Int?> = sudoku.grid.toList()
        get() = sudoku.grid.toList()
        set(value) {
            field = value
            sudoku = Sudoku(value.toTypedArray())
        }

    private fun shuffleRows(first: Int, second: Int) {
        if (!sudoku.isInSameGroup(first, second)) {
            throw IllegalArgumentException("Can't shuffle rows in different groups")
        }
        val rows = sudoku.gridRows
        val firstRow = rows[first]
        val secondRow = rows[second]
        val mutableGrid = grid.toMutableList()
        firstRow.forEachIndexed { index, element ->
            mutableGrid[second * 9 + index] = element
        }
        secondRow.forEachIndexed { index, element ->
            mutableGrid[first * 9 + index] = element
        }
        grid = mutableGrid
    }

    private fun shuffleColumns(first: Int, second: Int) {
        if (!sudoku.isInSameGroup(first, second)) {
            throw IllegalArgumentException("Can't shuffle columns in different groups")
        }
        val columns = sudoku.gridColumns
        val firstColumn = columns[first]
        val secondColumn = columns[second]
        val mutableGrid = grid.toMutableList()
        firstColumn.forEachIndexed { index, element ->
            mutableGrid[index * 9 + second] = element
        }
        secondColumn.forEachIndexed { index, element ->
            mutableGrid[index * 9 + first] = element
        }
        grid = mutableGrid
    }

    enum class Difficult(val gridSize: Int) {
        EASY(35),
        NORMAL(32),
        HARD(28)
    }

    fun generateSudoku(difficult: Difficult = Difficult.NORMAL): Sudoku {
        randomSudoku()
        while (checkGridFullness() > difficult.gridSize) {
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

    private fun checkGridFullness(): Int {
        var counter = 0
        grid.forEach {
            it?.let { counter++ }
        }
        return counter
    }

    private fun deleteRandomNumber() {
        val mutableGrid = grid.toMutableList()
        var deleted = false
        while (!deleted) {
            val randomIndex = Random.nextInt(0..mutableGrid.lastIndex)
            if (mutableGrid[randomIndex] != null) {
                mutableGrid[randomIndex] = null
                deleted = true
            }
        }
        grid = mutableGrid
    }

}