package com.example.sudokusolver.sudoku.solution

import com.example.sudokusolver.sudoku.Sudoku

class ReduceFromSquares : ReduceCandidates {
    override fun reduceCandidates(sudoku: Sudoku, candidates: List<Set<Int>>): List<Set<Int>> {
        val mutableCandidates = candidates.map { it.toMutableSet() }
        sudoku.grid.forEachIndexed { index, _ ->
            mutableCandidates[index].removeIf { sudoku.grid.getSquareByIndex(index).contains(it) }
        }
        return mutableCandidates
    }
}