package com.example.sudokusolver.sudoku.solution

import com.example.sudokusolver.sudoku.Sudoku

class ReduceSingleColumn: ReduceCandidates {
    override fun reduceCandidates(sudoku: Sudoku, candidates: List<Set<Int>>): List<Set<Int>> {
        val mutableCandidates = candidates.map { it.toMutableSet() }
        mutableCandidates.columns.forEach { column ->
            val singleCandidates = column.flatten()
                .groupBy { it }
                .filter { it.value.size == 1 }
                .map { it.key }
            singleCandidates.forEach { candidate ->
                column.first { it.contains(candidate) }
                    .removeIf { it != candidate }
            }
        }
        return mutableCandidates
    }
}