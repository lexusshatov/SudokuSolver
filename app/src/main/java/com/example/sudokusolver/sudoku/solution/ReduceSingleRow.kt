package com.example.sudokusolver.sudoku.solution

import com.example.sudokusolver.sudoku.Sudoku

class ReduceSingleRow: ReduceCandidates {
    override fun reduceCandidates(sudoku: Sudoku, candidates: List<Set<Int>>): List<Set<Int>> {
        val mutableCandidates = candidates.map { it.toMutableSet() }
        mutableCandidates.rows.forEach { row ->
            val singleCandidates = row.flatten()
                .groupBy { it }
                .filter { it.value.size == 1 }
                .map { it.key }
            singleCandidates.forEach { candidate ->
                row.first { it.contains(candidate) }
                    .removeIf { it != candidate }
            }
        }
        return mutableCandidates
    }
}