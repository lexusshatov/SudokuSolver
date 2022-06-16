package com.example.sudokusolver.sudoku.solution

fun <T> List<T>.getRowByIndex(index: Int): List<T> {
    val startIndex = index / 9 * 9
    return slice(startIndex until startIndex + 9)
}

fun <T> List<T>.getColumnByIndex(index: Int): List<T> {
    val startIndex = index % 9
    val result = mutableListOf<T>()
    repeat(9) { listIndex ->
        result.add(this[startIndex + listIndex * 9])
    }
    return result
}

fun <T> List<T>.getSquareByIndex(index: Int): List<T> {
    val square = mutableListOf<T>()
    val startIndex = index / 27 * 27 + index % 9 / 3 * 3
    repeat(3) { rowIndex ->
        repeat(3) { columnIndex ->
            square += this[(startIndex + rowIndex * 9) + columnIndex]
        }
    }
    return square
}

val <T> List<T>.rows: List<List<T>>
    get() = (0 until 81 step 9).map { getRowByIndex(it) }

val <T> List<T>.columns: List<List<T>>
    get() = (0 until 9).map { getColumnByIndex(it) }

val <T> List<T>.squares: List<List<T>>
    get() = (0 until 3).map { rowIndex ->
        (0 until 3).map { columnIndex ->
            rowIndex * 27 + columnIndex * 3
        }
    }.flatten().map { getSquareByIndex(it) }