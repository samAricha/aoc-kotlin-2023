package day01

import readFileInput
import java.lang.StringBuilder

fun main() {
    val file1 = "day01/day_01_1"
    val file2 = "day01/day_01_2"
    val file2test = "day01/day_01_test2"

    val fileInput1 = readFileInput(file1)
    val fileInput2Test = readFileInput(file2test)
    val fileInput2 = readFileInput(file2)


    val total1 = solvePart1(fileInput1)
    val total2 = solvePart2(fileInput2)
//    val total2 = fun2(fileInput1)
//    println("total1 :$total1")
        println("total2 :$total2")


}

val spelledOutDigits: Map<String, Int> = mapOf(
    "zero" to 0,
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)
fun parseFileInput(input: String):List<String> = input.split("\n")

fun solvePart1(input: String): Int {
    val parsedInput = parseFileInput(input)

    return parsedInput.sumOf { calibrationValue(it) }
}

private fun calibrationValue(row: String): Int {
    return "${row.first { it.isDigit() }}${row.last { it.isDigit() }}".toInt()
}

fun solvePart2(input: String): Int {
    val parsedInput = parseFileInput(input)

    return parsedInput.sumOf { row ->
        calibrationValue(
            row.mapIndexedNotNull { index, c ->
                if (c.isDigit()) c
                else
                    row.possibleWordsAt(index).firstNotNullOfOrNull { candidate ->
                        spelledOutDigits[candidate]
                    }
            }.joinToString()
        )
    }
}

private fun String.possibleWordsAt(startingAt: Int): List<String> =
    (3..5).map { len ->
        substring(startingAt, (startingAt + len).coerceAtMost(length))
    }









