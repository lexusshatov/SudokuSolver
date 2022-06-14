package com.example.sudokusolver.sudoku.solution

import com.example.sudokusolver.sudoku.Sudoku

class ReduceFilledCells : ReduceCandidates {
    override fun reduceCandidates(sudoku: Sudoku, candidates: List<Set<Int>>): List<Set<Int>> {
        return sudoku.grid.mapIndexed { index, cell ->
            if (cell != null) emptySet() else candidates[index]
        }
    }
}