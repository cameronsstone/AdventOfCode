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
        val fuel = crabs.sumOf { fuelCost2(p, it) }
        // var fuel = 0
        // for (crab in crabs) {
        //     val f = fuelCost2(crab, p)
        //     println(" - Move from $crab to $p: $f fuel")
        //     fuel += f
        // }
        if (fuel < minFuel) {
            minPos = p
            minFuel = fuel
        }
        println("position $p costs $fuel fuel")
    }
    println("pos $minPos, fuel $minFuel")
}

fun fuelCost(start: Int, end: Int): Int {
    return abs(start - end)
}
fun fuelCost2(start: Int, end: Int): Int {
    val distance = abs(start - end)
    val numTerms = distance + 1
    return numTerms * distance / 2
}

