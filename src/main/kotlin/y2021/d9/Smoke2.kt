package y2021.d9

import getResourceAsText
import y2021.d5.Coord

fun main() {
    println("Start")

    val input = getResourceAsText("d9/input")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val grid = lines.map { it.split("").filter(String::isNotEmpty).map(String::toInt) }

    val lowests = mutableListOf<Coord>()
    for (x in grid.indices) {
        for (y in grid[x].indices) {
            val coord = Coord(x, y)
            if (isLowestExcluding(grid, coord)) {
                lowests.add(coord)
            }
        }
    }
    println("found ${lowests.size} basins")

    val basins = mutableMapOf<Coord, Set<Coord>>()
    for (basin in lowests) {
        val set = mutableSetOf<Coord>()
        val queue = ArrayDeque(listOf(basin))
        do {
            val coord = queue.removeFirst()
            if (coord !in set && isLowestExcluding(grid, coord, set)) {
                set.add(coord)

                if (coord.x != 0) {
                    queue.add(Coord(coord.x - 1, coord.y))
                }
                if (coord.x < grid.size - 1) {
                    queue.add(Coord(coord.x + 1, coord.y))
                }
                if (coord.y != 0) {
                    queue.add(Coord(coord.x, coord.y - 1))
                }
                if (coord.y < grid[coord.x].size - 1) {
                    queue.add(Coord(coord.x, coord.y + 1))
                }
            }
        } while (queue.isNotEmpty())
        basins[basin] = set
    }

    val sortedBasins = basins.entries.sortedByDescending { it.value.size }
    for ((lowest, set) in sortedBasins) {
        println("lowest $lowest has size ${set.size} yields ${printBasin(grid, set)}")
    }

    val largest = sortedBasins.take(3)
    println("largest 3 basins are:")
    var product = 1
    for ((lowest, squares) in largest) {
        println("$lowest has size ${squares.size}")
        product *= squares.size
    }
    println("product is $product")

    val basinCoords: Set<Coord> = largest.map { it.value }.reduce { coords, other -> coords.union(other) }
    for (x in grid.indices) {
        for (y in grid[x].indices) {
            val coord = Coord(x, y)
            if (coord in basinCoords) {
                print("\\e[1m${grid[x][y]}\\e[0m")
            } else {
                print(grid[x][y])
            }
        }
        println()
    }
}

fun printBasin(grid: List<List<Int>>, set: Set<Coord>): String {
    return set.sortedWith(compareBy({ it.x }, { it.y })).joinToString(",", "[", "]") {
        "(${it.x},${it.y}):${grid[it.x][it.y]}"
    }
}

fun isLowestExcluding(grid: List<List<Int>>, coord: Coord, set: Set<Coord> = emptySet()): Boolean {
    val x = coord.x
    val y = coord.y
    val coordHeight = grid[x][y]
    if (coordHeight == 9) {
        return false
    }
    if (x != 0 && Coord(x - 1, y) !in set && grid[x - 1][y] < coordHeight) {
        return false
    }
    if (x < grid.size - 1 && Coord(x + 1, y) !in set && grid[x + 1][y] < coordHeight) {
        return false
    }
    if (y != 0 && Coord(x, y - 1) !in set && grid[x][y - 1] < coordHeight) {
        return false
    }
    if (y < grid[x].size - 1 && Coord(x, y + 1) !in set && grid[x][y + 1] < coordHeight) {
        return false
    }
    return true
}

