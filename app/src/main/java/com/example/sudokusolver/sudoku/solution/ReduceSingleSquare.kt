package com.example.sudokusolver.sudoku.solution

import com.example.sudokusolver.sudoku.Sudoku

class ReduceSingleSquare: ReduceCandidates {
    override fun reduceCandidates(sudoku: Sudoku, candidates: List<Set<Int>>): List<Set<Int>> {
        val mutableCandidates = candidates.map { it.toMutableSet() }
        mutableCandidates.squares.forEach { square ->
            val singleCandidates = square.flatten()
                .groupBy { it }
                .filter { it.value.size == 1 }
                .map { it.key }
            singleCandidates.forEach { candidate ->
                square.first { it.contains(candidate) }
                    .removeIf { it != candidate }
            }
        }
        return mutableCandidates
    }
}