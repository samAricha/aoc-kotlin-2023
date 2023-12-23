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


//    val total1 = fun1(fileInput1)
    val total2 = fun2(fileInput2)
    println("total2 :$total2")


}

fun fun1(fileInput: String) : Int {
    val parsedInput = parseInput(fileInput)
    val extractedDigits:List<Int> = parsedInput.mapNotNull { extractAndCombine(it) }
    val totalSum: Int = extractedDigits.sum()
    println(totalSum)
    return totalSum
}

fun fun2(fileInput: String) : Int {
    val parsedInput = parseInput(fileInput)
    val cleanList = processStringList(parsedInput)
//    val cleanList = processStringListv2(parsedInput)

    println(cleanList)
    val extractedDigits:List<Int> = cleanList.mapNotNull { extractAndCombine(it) }
    val totalSum: Int = extractedDigits.sum()
    println(totalSum)
    return totalSum
}
fun parseInput(input: String):List<String> = input.split("\n")

fun extractAndCombine(input: String): Int?{

    val digits = StringBuilder()

    for(char in input){
        if (char.isDigit()){
            digits.append(char)
        }
    }

    if (digits.isNotEmpty()){
        val firstDigit = digits[0].toString().toInt()
        val lastDigit = digits[digits.length -1].toString().toInt()
//        println("$firstDigit$lastDigit".toInt())
        return "$firstDigit$lastDigit".toInt()
    }

    return null
}





