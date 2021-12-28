package y2021.d9

import getResourceAsText

fun main() {
    println("Start")

    val input = getResourceAsText("d9/test")
    val lines = input.trim().split('\n')
    println("read ${lines.size} lines")

    val grid = lines.map { it.split("").filter(String::isNotEmpty).map(String::toInt) }

    var sum = 0
    for (x in grid.indices) {
        for (y in grid[x].indices) {
            if (isLowest(grid, x, y)) {
                val risk = grid[x][y] + 1
                println("($x, $y) is lowest with risk: $risk")
                sum += risk
            }
        }
    }
    println("total risk is $sum")
}

fun isLowest(grid: List<List<Int>>, x: Int, y: Int): Boolean {
    if (x != 0 && grid[x - 1][y] <= grid[x][y]) {
        return false
    }
    if (x < grid.size - 1 && grid[x][y] >= grid[x + 1][y]) {
        return false
    }
    if (y != 0 && grid[x][y - 1] <= grid[x][y]) {
        return false
    }
    if (y < grid[x].size - 1 && grid[x][y] >= grid[x][y + 1]) {
        return false
    }
    return true
}

