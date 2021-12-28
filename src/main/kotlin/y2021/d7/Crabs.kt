package y2021.d7

import getResourceAsText
import kotlin.math.abs

fun main() {
    println("Start")

    val input = getResourceAsText("d7/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val crabs = lines[0].split(",").map(String::toInt)
    val min = crabs.minOrNull()!!
    val max = crabs.maxOrNull()!!

    var minPos = min
    var minFuel = Int.MAX_VALUE
    for (p in min..max) {
        val fuel = crabs.sumOf { abs(it - p) }
        if (fuel < minFuel) {
            minPos = p
            minFuel = fuel
        }
    }
    println("pos $minPos, fuel $minFuel")
}

