package y2021.d3

import getResourceAsText

fun main(args: Array<String>) {
    println("Start")

    val input = getResourceAsText("d3/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val codes: List<CharArray> = lines.map(String::toCharArray)

    val size = codes[0].size
    var O2 = codes
    var CO2 = codes
    println("O2: ${O2.map(::String)}")
    println("CO2: ${CO2.map(::String)}")
    for (i in 0 until size) {
        println(i)
        if (O2.size > 1) {
            var excessOnesInO2 = 0
            for (code in O2)
                when {
                    code[i] == ONE -> excessOnesInO2++
                    else -> excessOnesInO2--
                }
            O2 = if (excessOnesInO2 >= 0)
                O2.filter { code -> code[i] == ONE }
            else
                O2.filter { code -> code[i] == ZERO }

            println("O2: ${O2.map(::String)}")
        }
        if (CO2.size > 1) {
            var excessOnesInCO2 = 0
            for (code in CO2)
                when {
                    code[i] == ONE -> excessOnesInCO2++
                    else -> excessOnesInCO2--
                }

            CO2 = if (excessOnesInCO2 >= 0)
                CO2.filter { code -> code[i] == ZERO }
            else
                CO2.filter { code -> code[i] == ONE }

            println("CO2: ${CO2.map(::String)}")
        }
    }

    println()
    val O2Rate = String(O2[0]).toInt(2)
    val CO2Rate = String(CO2[0]).toInt(2)
    println("O2: $O2Rate (${String(O2[0])}) CO2: $CO2Rate (${String(CO2[0])})")

    println(O2Rate * CO2Rate)
}
