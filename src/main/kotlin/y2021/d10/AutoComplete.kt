package y2021.d10

import getResourceAsText
import java.util.Stack

fun main() {
    val match = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    val scoreMap = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    println("Start")

    val input = getResourceAsText("d10/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val lineSums = mutableListOf<Long>()
    lines@ for (line in lines) {
        val stack = Stack<Char>()
        for (char in line) {
            val closer = match[char]
            if (closer != null) {
                stack.push(closer)
            } else {
                val expectedCloser = stack.pop()
                if (expectedCloser != char) {
                    continue@lines
                }
            }
        }
        var lineSum = 0L
        val missing = mutableListOf<Char>()
        while (stack.isNotEmpty()) {
            val prev = lineSum
            lineSum *= 5
            val char = stack.pop()
            missing.add(char)
            val score = scoreMap[char] ?: throw AssertionError("invalid char $char")
            lineSum += score
            println("line score is now $lineSum ($prev * 5 + $score)")
        }
        println("Complete $line by adding ${String(missing.toCharArray())} - $lineSum total points.")
        lineSums += lineSum
    }
    println("found ${lineSums.size} incomplete lines")
    val index = ((lineSums.size + 1) / 2) - 1
    val median = lineSums.sorted()[index]
    println("median ($index) value is $median")
}

