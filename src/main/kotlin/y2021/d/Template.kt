package y2021.d

import getResourceAsText

fun main() {
    println("Start")

    val input = getResourceAsText("d/test")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")


}

