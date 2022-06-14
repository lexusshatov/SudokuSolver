package com.example.sudokusolver.sudoku.solution

import com.example.sudokusolver.sudoku.Sudoku

interface SolveChain : SolveSudoku {
    val chain: List<ReduceCandidates>
    override fun solve(sudoku: Sudoku): Sudoku

    class Base : SolveChain {

        override val chain: List<ReduceCandidates> = listOf(
            ReduceFilledCells(),
            ReduceFromRows(),
            ReduceFromColumns(),
            ReduceFromSquares()
        )

        override fun solve(sudoku: Sudoku): Sudoku {
            var resultSudoku = sudoku
            var candidates = generateSequence { (1..9).toSet() }.take(9 * 9).toList()

            var isElementAdded: Boolean
            do {
                isElementAdded = false
                chain.forEach {
                    candidates = it.reduceCandidates(resultSudoku, candidates)
                }
                candidates.forEachIndexed { index, set ->
                    if (set.size == 1) {
                        val grid = resultSudoku.grid.toMutableList()
                        grid[index] = set.first()
                        resultSudoku = Sudoku(grid)
                        isElementAdded = true
                    }
                }
            } while (isElementAdded)
            return resultSudoku
        }
    }
}