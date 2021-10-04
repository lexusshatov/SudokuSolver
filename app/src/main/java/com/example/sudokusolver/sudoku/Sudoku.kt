package com.example.sudokusolver.sudoku

data class Sudoku(
    val grid: Array<Int?> = arrayOf(
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
) {

    init {
        if (!isCorrectGrid()) throw IllegalArgumentException("Wrong grid")
    }

    val gridRows: List<List<Int?>>
        get() {
            val result: MutableList<List<Int?>> = mutableListOf()
            for (rowIndex in 0..grid.lastIndex step 9) {
                result += getRowByIndex(rowIndex)
            }
            return result
        }

    val gridColumns: List<List<Int?>>
        get() {
            val result: MutableList<List<Int?>> = mutableListOf()
            repeat(9) { columnIndex ->
                result += getColumnByIndex(columnIndex)
            }
            return result
        }

    val gridSquares: List<List<Int?>>
        get() {
            val result: MutableList<List<Int?>> = mutableListOf()
            repeat(3) { rowIndex ->
                repeat(3) { columnIndex ->
                    val startIndex = rowIndex * 27 + columnIndex * 3
                    result += getSquareByIndex(startIndex)
                }
            }
            return result
        }

    fun getRowByIndex(index: Int): List<Int?> {
        val startIndex = index / 9 * 9
        return grid.slice(startIndex until startIndex + 9)
    }

    fun getColumnByIndex(index: Int): List<Int?> {
        val startIndex = index % 9
        val result: MutableList<Int?> = mutableListOf()
        repeat(9) { listIndex ->
            result.add(grid[startIndex + listIndex * 9])
        }
        return result
    }

    fun getSquareByIndex(index: Int): List<Int?> {
        val square: MutableList<Int?> = mutableListOf()
        val startIndex = index / 27 * 27 + index % 9 / 3 * 3
        repeat(3) { rowIndex ->
            repeat(3) { columnIndex ->
                square += grid[(startIndex + rowIndex * 9) + columnIndex]
            }
        }
        return square
    }

    fun isInSameGroup(first: Int, second: Int): Boolean {
        return groups.any { group -> first in group && second in group }
    }

    private fun isCorrectGrid(): Boolean {
        return grid.size == 81
                && grid.all { it in 1..9 || it == null }
                && gridRows.all { it.filterNotNull().isSet() }
                && gridColumns.all { it.filterNotNull().isSet() }
                && gridSquares.all { it.filterNotNull().isSet() }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sudoku

        if (!grid.contentEquals(other.grid)) return false

        return true
    }

    override fun hashCode(): Int {
        return grid.contentHashCode()
    }

    private fun <T> List<T>.isSet(): Boolean {
        return size == toSet().size
    }

    companion object {
        val groups = listOf(0..2, 3..5, 6..8)
    }

}
