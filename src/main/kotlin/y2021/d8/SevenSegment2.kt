package y2021.d8

import getResourceAsText

fun main() {
    println("Start")

    val input = getResourceAsText("d8/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    var sum = 0
    for (line in lines) {
        val (codeSide, outputSide) = line.split("|").map(String::trim)
        val codes = codeSide.split(" ")
        val map = HashMap<String, Int>()
        val one = codes.first { it.length == 2 }
        map[one.sort()] = 1
        val seven = codes.first { it.length == 3 }
        map[seven.sort()] = 7
        val four = codes.first { it.length == 4 }
        map[four.sort()] = 4
        val eight = codes.first { it.length == 7 }
        map[eight.sort()] = 8
        val nine = codes.first { it.length == 6 && it.containsAllChars(seven) && it.containsAllChars(four) }
        map[nine.sort()] = 9
        val zero = codes.first { it.length == 6 && it.containsAllChars(one) && it != nine }
        map[zero.sort()] = 0
        val six = codes.first { it.length == 6 && it != nine && it != zero }
        map[six.sort()] = 6
        val three = codes.first { it.length == 5 && it.containsAllChars(one) }
        map[three.sort()] = 3
        val five = codes.first { it.length == 5 && six.containsAllChars(it) }
        map[five.sort()] = 5
        val two = codes.first { it.length == 5 && it != three && it != five }
        map[two.sort()] = 2

        val output = outputSide.split(" ")
        var num = 0
        for (code in output) {
            num *= 10
            num += map[code.sort()]!!
        }
        println("$outputSide: $num")
        sum += num
    }
    println("\nSum is $sum")
}

private fun String.sort(): String {
    return this.toCharArray().sorted().joinToString("")
}

private fun String.containsAllChars(substring: String): Boolean {
    for (c in substring) {
        if (!this.contains(c)) {
            return false
        }
    }
    return true
}

