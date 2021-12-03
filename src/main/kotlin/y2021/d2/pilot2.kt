package y2021.d2

import getResourceAsText
import java.io.FileNotFoundException
import java.lang.Integer.max


fun main(args: Array<String>) {
    println("Start")

    val input = getResourceAsText("d2/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val commands = lines.map(::convert)

    var depth = 0
    var position = 0
    var aim = 0
    for ((dir, value) in commands) {
        when (dir) {
            Direction.up -> aim -= value
            Direction.down -> aim += value
            Direction.forward -> {
                position += value
                depth += (aim * value)
            }
        }
        println("$dir $value -> pos: $position depth: $depth aim: $aim")
    }
    println(depth * position)
}

