package y2021.d6

import getResourceAsText
import java.lang.AssertionError

fun main() {
    println("Start")

    val input = getResourceAsText("d6/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val initial = lines[0].split(",").map(String::toInt)

    println("Initial state: ${initial.d()}")

    val fish = MutableList(9) { 0L }

    for (f in initial) {
        fish[f]++
    }

    val days = 256
    var day = 0
    while (day < days) {
        day++
        val newFish = fish.removeFirst()
        fish[6] += newFish
        if (fish[6] < 0) {
            throw AssertionError("Overflow after $day days!")
        }
        fish.add(newFish)
        // println("After ${"%2d".format(day)} days: ${fish.d()}")
    }
    println("After ${"%2d".format(days)} days, there are ${fish.sum()} lanternfish")
}
private fun <E> List<E>.d() = this.joinToString(",")

