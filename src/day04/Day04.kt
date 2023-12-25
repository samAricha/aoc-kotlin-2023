package day04

import readInput
import kotlin.math.pow

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
    val currentWorkingInput1 = fileInput1
    val currentWorkingInput2 = fileInput2

    val cardMatches = currentWorkingInput1.map { parseCard(it) }
    val cardMatches2 = currentWorkingInput2.map { parseCard(it) }

//    println(cardMatches)
    val part1Ans = solvePart1(cardMatches)
    val part2Ans = solvePart2(cardMatches2)
    println(part2Ans)

}

fun solvePart1(cardMatches:List<Int>): Int{
    val result = cardMatches.sumOf { 2.0.pow(it-1).toInt() }
    return result
}

fun solvePart2(cardMatches:List<Int>): Int{

    val cards = IntArray(cardMatches.size) { 1 }
    cardMatches.forEachIndexed { index, score ->
        repeat(score) {
            cards[index+it+1] += cards[index]
        }
    }
    return cards.sum()
}


private fun parseCard(input: String): Int {
    val winningNumbers = input.substringAfter(":")
        .substringBefore("|")
        .split(" ")
        .filter { it.isNotEmpty() }
        .toSet()

    val ourNumbers = input.substringAfter("|")
        .split(" ")
        .filter { it.isNotEmpty() }
        .toSet()

    return winningNumbers.intersect(ourNumbers).size
}

