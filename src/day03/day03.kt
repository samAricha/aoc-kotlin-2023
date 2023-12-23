package day03

import day03.Point2D
import readInput

fun main() {
    val file1test = "day04/test1"
    val file2test = "day04/test2"
    val file1 = "day04/file1"
    val file2 = "day04/file2"

    val fileInput1 = readInput(file1)
    val fileInput2 = readInput(file2)
    val fileInputTest1:List<String> = readInput(file1test)
    val fileInputTest2 = readInput(file2test)

    //just a value for holding file i'm currently working on
    val currentWorkingInput = fileInput1
    val currentWorkingInput2 = fileInput2

//    println(currentWorkingInput)
//    println(solvePart1(currentWorkingInput))
    println(solvePart2(currentWorkingInput2))


}

fun solvePart1(input: List<String>): Int{
    val (numbers, symbols) = parseInput(input)
    return numbers
        .filter { number -> number.isAdjacentToAny(symbols) }
        .sumOf { it.toInt() }
}

fun solvePart2(input: List<String>): Int {
    val (numbers, symbols) = parseInput(input) { it == '*' }
    return symbols
        .sumOf { symbol ->
            val neighbors = numbers.filter { it.isAdjacentTo(symbol) }
            if(neighbors.size == 2) {
                neighbors.first().toInt() * neighbors.last().toInt()
            } else 0
        }
}



data class Point2D(val x: Int, val y: Int) {
    fun neighbors(): Set<Point2D> =
        setOf(
            Point2D(x - 1, y - 1),
            Point2D(x, y - 1),
            Point2D(x + 1, y - 1),
            Point2D(x - 1, y),
            Point2D(x + 1, y),
            Point2D(x - 1, y + 1),
            Point2D(x, y + 1),
            Point2D(x + 1, y + 1)
        )
}


private class NumberLocation {
    val number = mutableListOf<Char>()
    val locations = mutableSetOf<Point2D>()

    fun add(c: Char, location: Point2D) {
        number.add(c)
        locations.addAll(location.neighbors())
    }

    fun isNotEmpty() =
        number.isNotEmpty()

    fun isAdjacentToAny(points: Set<Point2D>): Boolean =
        locations.intersect(points).isNotEmpty()

    fun toInt(): Int =
        number.joinToString("").toInt()

    fun isAdjacentTo(point: Point2D): Boolean =
        point in locations
}


private fun parseInput(
    input: List<String>,
    takeSymbol: (Char) -> Boolean = { it != '.' }
): Pair<Set<NumberLocation>, Set<Point2D>> {

    val numbers = mutableSetOf<NumberLocation>()
    val symbols = mutableSetOf<Point2D>()
    var workingNumber = NumberLocation()

    input
        .forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c.isDigit()) {
                    workingNumber.add(c, Point2D(x, y))
                } else {
                    if (workingNumber.isNotEmpty()) {
                        numbers.add(workingNumber)
                        workingNumber = NumberLocation()
                    }
                    if(takeSymbol(c)) {
                        symbols.add(Point2D(x, y))
                    }
                }
            }
            // Check at end of row that we don't miss a number.
            if (workingNumber.isNotEmpty()) {
                numbers.add(workingNumber)
                workingNumber = NumberLocation()
            }
        }
    return numbers to symbols
}
