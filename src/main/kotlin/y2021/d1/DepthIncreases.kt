package y2021.d1

import getResourceAsText
import java.io.FileNotFoundException

fun main(args: Array<String>) {
    println("Start")

    val input = getResourceAsText("d1/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    var count = 0
    for (i in 1 until lines.size) {
        if (lines[i] > lines[i-1]) {
            count++
        } else
            println("line ${i+1}: ${lines[i]} is less than ${lines[i-1]}")
    }
    println(count)
}

