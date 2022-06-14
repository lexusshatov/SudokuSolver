package com.example.sudokusolver.sudoku.solution

import com.example.sudokusolver.sudoku.Sudoku

interface ReduceCandidates {
    fun reduceCandidates(sudoku: Sudoku, candidates: List<Set<Int>>): List<Set<Int>>
}