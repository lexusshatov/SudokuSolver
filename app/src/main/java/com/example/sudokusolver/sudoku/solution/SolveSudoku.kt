package com.example.sudokusolver.sudoku.solution

import com.example.sudokusolver.sudoku.Sudoku

interface SolveSudoku {
    fun solve(sudoku: Sudoku): Sudoku
}