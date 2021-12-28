package y2021.d6

import getResourceAsText

fun main() {
    println("Start")

    val input = getResourceAsText("d6/test")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val fish = lines[0].split(",").map(String::toInt).toMutableList()

    println("Initial state: ${fish.d()}")
    val days = 80
    var day = 0
    while (day < days) {
        day++
        for (f in fish.indices.reversed()) {
            fish[f]--
            if (fish[f] < 0) {
                fish[f] = 6
                fish.add(8)
            }
        }
        // println("After ${"%2d".format(day)} days: ${fish.d()}")
    }
    println("After ${"%2d".format(days)} days, there are ${fish.size} lanternfish")
}

private fun <E> List<E>.d() = this.joinToString(",")

