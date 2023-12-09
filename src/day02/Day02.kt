package day02

import readFileInput

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

fun solvePart1(input: String){

}

fun solvePart2(input: String){

}