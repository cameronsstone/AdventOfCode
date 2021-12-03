package y2021.d1

import getResourceAsText
import java.io.FileNotFoundException

fun main(args: Array<String>) {
    val input = getResourceAsText("d1/input")
    val lines = input.trim().split('\n').map(String::toInt)
    println("read ${lines.size} lines")

    var count = 0
    var previous = lines[0] + lines[1] + lines[2]
    for (i in 1 .. lines.size - 3) {
        val current = previous - lines[i-1] + lines[i+2]
        if (current > previous) {
            count++
        } else
            println("line ${i+1}: $current is less than $previous")
        previous = current
    }
    println(count)
}

