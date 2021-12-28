package y2021.d4

import getResourceAsText

fun main(args: Array<String>) {
    println("Start")

    val input = getResourceAsText("d4/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val numbers = lines[0].split(',').map(String::toInt)

    val numBoards = (lines.size - 1) / 6
    val boards = Array(numBoards) {
        Array(5) {
            IntArray(5)
        }
    }
    val drawn = Array(numBoards) {
        Array(5) {
            BooleanArray(5)
        }
    }
    val boardWon = BooleanArray(numBoards)

    for (b in boards.indices) {
        for (r in boards[b].indices) {
            val l = 2 + (b * 6) + r
            val line = lines[l]
            val squares = line.trim().split(Regex(" +"))
            boards[b][r] = squares.map(String::toInt).toIntArray()
        }
    }

    for (number in numbers) {
        for ((b, board) in boards.withIndex()) {
            if (boardWon[b]) {
                continue
            }
            for ((r, row) in board.withIndex()) {
                for ((c, square) in row.withIndex()) {
                    if (square == number) {
                        drawn[b][r][c] = true
                        println("square found in board $b, ($r, $c)")
                        if (rowWin(drawn[b], r)) {
                            println("board $b has bingo in row $r")
                            printHash(board, drawn[b], number)
                            boardWon[b] = true
                        } else if (columnWin(drawn[b], c)) {
                            println("board $b has bingo in column $c")
                            printHash(board, drawn[b], number)
                            boardWon[b] = true
                        }
                    }
                }
            }
        }
    }

    // println("numbers are $numbers")
    // for (board in boards) {
    //     println()
    //     for (row in board) {
    //         println(row.toList())
    //     }
    // }
}

fun rowWin(drawn: Array<BooleanArray>, r: Int): Boolean {
    for (square in drawn[r]) {
        if (!square) {
            return false
        }
    }
    return true
}

fun columnWin(drawn: Array<BooleanArray>, c: Int): Boolean {
    for (i in drawn.indices) {
        if (!drawn[i][c]) {
            return false
        }
    }
    return true
}

fun printHash(board: Array<IntArray>, drawn: Array<BooleanArray>, number: Int) {
    var sum = 0
    for ((r, row) in board.withIndex()) {
        for ((c, square) in row.withIndex()) {
            if (!drawn[r][c]) {
                sum += square
            }
        }
    }
    println("final score is $sum * $number = ${sum * number}")
}
