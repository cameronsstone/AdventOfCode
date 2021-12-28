package y2021.d8

import getResourceAsText

fun main() {
    println("Start")

    val input = getResourceAsText("d8/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val outputs = lines.map { it.split("|")[1].trim().split(" ").map(String::trim) }.flatten()

    val interestingLengths = listOf(2, 4, 3, 7)
    val interestingOutputs = outputs.filter { it.length in interestingLengths }
    println(interestingOutputs.size)
}

