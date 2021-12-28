package y2021.d5

import getResourceAsText

fun main() {
    println("Start")

    val input = getResourceAsText("d5/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val segments = lines.map { line ->
        line.split(" -> ").map { endpoints ->
            endpoints.split(",").map(String::toInt).toCoord()
        }.toVentLine()
    }

    val vents = HashMap<Coord, Int>()
    for (segment in segments) {
        // if (segment.isDiagonal()) {
        //     continue
        // }
        for (vent in segment.vents()) {
            vents[vent] = vents.getOrDefault(vent, 0) + 1
            // println("adding to $vent (total ${vents[vent]})")
        }
    }

    val numDanger = vents.values.filter { it > 1 }.size
    println("found $numDanger dangerous areas")
}

private fun List<Coord>.toVentLine() = VentLine(this[0], this[1])

private fun List<Int>.toCoord(): Coord = Coord(this[0], this[1])

data class Coord(val x: Int, val y: Int) {
    operator fun plus(coord: Coord) = Coord(x + coord.x, y + coord.y)
}

data class VentLine(val start: Coord, val end: Coord) {
    fun vents() = sequence {
        val deltaX: Int = when {
            end.x > start.x  -> 1
            end.x < start.x -> -1
            else -> 0
        }
        val deltaY: Int = when {
            end.y > start.y  -> 1
            end.y < start.y -> -1
            else -> 0
        }
        val delta = Coord(deltaX, deltaY)
        var current = start
        var moreVents: Boolean
        do {
            yield(current)
            moreVents = (current != end)
            current += delta
        } while (moreVents)
    }

    fun isDiagonal() = start.x != end.x && start.y != end.y
}

