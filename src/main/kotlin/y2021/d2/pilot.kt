package y2021.d2

import getResourceAsText
import java.io.FileNotFoundException
import java.lang.Integer.max

enum class Direction {
    forward, down, up
}

fun String.toDir(): Direction {
    return Direction.valueOf(this)
}

fun convert(line: String): Pair<Direction, Int> {
    val (dir, dist) = line.split(' ')
    return Pair(dir.toDir(), dist.toInt())
}

fun main(args: Array<String>) {
    println("Start")

    val input = getResourceAsText("d2/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val commands = lines.map(::convert)

    var depth = 0
    var position = 0
    for ((dir, dist) in commands) {
        when (dir) {
            Direction.up -> depth = max(0, depth - dist)
            Direction.down -> depth += dist
            Direction.forward -> position += dist
        }
        println("$dir $dist -> pos: $position depth: $depth")
    }
    println(depth * position)
}

