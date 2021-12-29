package y2021.d11

import getResourceAsText

fun main() {
    println("Start")
    val neighbours = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)

    val input = getResourceAsText("d11/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val octos = lines.map { it.split("").filter(String::isNotEmpty).map(String::toInt).toMutableList() }.toMutableList()
    println("Before any steps:")
    printOctos(octos)

    val steps = 1000
    var flashes = 0

    for (step in 1..steps) {
        for (y in octos.indices) {
            for (x in octos[y].indices) {
                octos[y][x]++
            }
        }

        var flashed: Boolean
        do {
            flashed = false

            for (y in octos.indices) {
                for (x in octos[y].indices) {
                    if (octos[y][x] > 9) {
                        octos[y][x] = 0
                        flashed = true
                        flashes++

                        for (offset in neighbours) {
                            val neighbourY = y + offset.first
                            val neighbourX = x + offset.second
                            if (neighbourY in octos.indices
                                && neighbourX in octos[neighbourY].indices
                                && octos[neighbourY][neighbourX] != 0
                            ) {
                                octos[neighbourY][neighbourX]++
                            }
                        }
                    }
                }
            }
        } while (flashed)

        println("After step $step:")
        printOctos(octos)

        var allFlashed = true
        outer@ for (y in octos.indices) {
            for (x in octos[y].indices) {
                if (octos[y][x] != 0) {
                    allFlashed = false
                    break@outer
                }
            }
        }
        if (allFlashed) {
            println("All octopuses first flashed at step $step")
            break
        }
    }

    println("After $steps steps, there have been a total of $flashes flashes")
}

fun printOctos(octos: List<List<Int>>) {
    println(octos.joinToString("\n") { it.joinToString("") })
    println()
}

