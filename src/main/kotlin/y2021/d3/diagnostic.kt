package y2021.d3

import getResourceAsText

const val ONE = '1'
const val ZERO = '0'

fun main(args: Array<String>) {
    println("Start")

    val input = getResourceAsText("d3/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val codes = lines.map(String::toCharArray)

    val size = codes[0].size
    val gamma = CharArray(size)
    val epsilon = CharArray(size)
    for (i in 0 until size) {
        var excessOnes = 0
        for (code in codes) {
            when {
                code[i] == ONE -> excessOnes++
                else -> excessOnes--
            }
        }
        if (excessOnes > 0) {
            gamma[i] = ONE
            epsilon[i] = ZERO
        } else {
            gamma[i] = ZERO
            epsilon[i] = ONE
        }
    }
    val gammaRate = String(gamma).toInt(2)
    val epsilonRate = String(epsilon).toInt(2)
    println("gamma: $gammaRate (${String(gamma)}) epsilon: $epsilonRate (${String(epsilon)})")

    println(gammaRate * epsilonRate)
}
