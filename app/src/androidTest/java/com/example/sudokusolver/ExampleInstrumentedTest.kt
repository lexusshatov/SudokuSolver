package com.example.sudokusolver

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.sudokusolver.sudoku.Sudoku
import com.example.sudokusolver.sudoku.SudokuGenerator
import com.example.sudokusolver.sudoku.solution.columns
import com.example.sudokusolver.sudoku.solution.rows
import com.example.sudokusolver.sudoku.solution.squares
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val grid: List<Int> = listOf(
        9, 5, 1, 7, 3, 2, 4, 8, 6,
        8, 4, 7, 6, 9, 1, 3, 5, 2,
        2, 6, 3, 5, 8, 4, 9, 7, 1,

        4, 1, 5, 9, 2, 8, 7, 6, 3,
        3, 2, 6, 1, 7, 5, 8, 9, 4,
        7, 8, 9, 4, 6, 3, 2, 1, 5,

        6, 7, 2, 3, 1, 9, 5, 4, 8,
        5, 9, 8, 2, 4, 6, 1, 3, 7,
        1, 3, 4, 8, 5, 7, 6, 2, 9
    )

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.sudokusolver", appContext.packageName)
    }

    @Test
    fun sudokuCalculator() {
        val defaultSudoku = Sudoku()
        println("Default Sudoku: $defaultSudoku")

        val sudoku = SudokuGenerator.generateSudoku()
        println("Random Sudoku: $sudoku")


        kotlin.runCatching {
            val wrongGrid = sudoku.grid.toMutableList().apply { this[lastIndex] = 0 }
            Sudoku(wrongGrid)
        }.getOrElse {
            println(it.localizedMessage)
        }
    }

    @Test
    fun rows() {
        val expected = listOf(
            listOf(9, 5, 1, 7, 3, 2, 4, 8, 6),
            listOf(8, 4, 7, 6, 9, 1, 3, 5, 2),
            listOf(2, 6, 3, 5, 8, 4, 9, 7, 1),

            listOf(4, 1, 5, 9, 2, 8, 7, 6, 3),
            listOf(3, 2, 6, 1, 7, 5, 8, 9, 4),
            listOf(7, 8, 9, 4, 6, 3, 2, 1, 5),

            listOf(6, 7, 2, 3, 1, 9, 5, 4, 8),
            listOf(5, 9, 8, 2, 4, 6, 1, 3, 7),
            listOf(1, 3, 4, 8, 5, 7, 6, 2, 9)
        )
        assertEquals(expected, grid.rows)
    }

    @Test
    fun columns() {
        val expected = listOf(
            listOf(9, 8, 2, 4, 3, 7, 6, 5, 1),
            listOf(5, 4, 6, 1, 2, 8, 7, 9, 3),
            listOf(1, 7, 3, 5, 6, 9, 2, 8, 4),
            listOf(7, 6, 5, 9, 1, 4, 3, 2, 8),
            listOf(3, 9, 8, 2, 7, 6, 1, 4, 5),
            listOf(2, 1, 4, 8, 5, 3, 9, 6, 7),
            listOf(4, 3, 9, 7, 8, 2, 5, 1, 6),
            listOf(8, 5, 7, 6, 9, 1, 4, 3, 2),
            listOf(6, 2, 1, 3, 4, 5, 8, 7, 9)
        )
        assertEquals(expected, grid.columns)
    }

    @Test
    fun squares() {
        val expected = listOf(
            listOf(9, 5, 1, 8, 4, 7, 2, 6, 3),
            listOf(7, 3, 2, 6, 9, 1, 5, 8, 4),
            listOf(4, 8, 6, 3, 5, 2, 9, 7, 1),
            listOf(4, 1, 5, 3, 2, 6, 7, 8, 9),
            listOf(9, 2, 8, 1, 7, 5, 4, 6, 3),
            listOf(7, 6, 3, 8, 9, 4, 2, 1, 5),
            listOf(6, 7, 2, 5, 9, 8, 1, 3, 4),
            listOf(3, 1, 9, 2, 4, 6, 8, 5, 7),
            listOf(5, 4, 8, 1, 3, 7, 6, 2, 9)
        )
        assertEquals(expected, grid.squares)
    }
}

