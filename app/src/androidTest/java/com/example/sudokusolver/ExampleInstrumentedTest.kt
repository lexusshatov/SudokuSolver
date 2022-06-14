package com.example.sudokusolver

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.sudokusolver.sudoku.Sudoku
import com.example.sudokusolver.sudoku.SudokuGenerator
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
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.sudokusolver", appContext.packageName)
    }

    @Test
    fun testSudokuCalculator() {
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
}

