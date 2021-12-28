package y2021.d10

import getResourceAsText
import java.util.Stack

fun main() {
    val match = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    val scoreMap = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)

    println("Start")

    val input = getResourceAsText("d10/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    var sum = 0
    for (line in lines) {
        val stack = Stack<Char>()
        for (char in line) {
            val closer = match[char]
            if (closer != null) {
                stack.push(closer)
            } else {
                val expectedCloser = stack.pop()
                if (expectedCloser != char) {
                    val score = scoreMap[char] ?: throw AssertionError("invalid char $char")
                    println("$line - Expected $expectedCloser, but found $char instead.")
                    sum += score
                }
            }
        }
    }
    println("sum of errors is $sum")
}

