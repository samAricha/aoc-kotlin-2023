package day03

import readFileInput

fun main() {
    val file1test = "day03/day_03_test2"
    val file2test = "day03/day_03_test1"

    val file1 = "day03/day_03_1"
    val file2 = "day03/day_03_2"



    val fileInput1 = readFileInput(file1)
    val fileInput2 = readFileInput(file2)
    val fileInputTest1 = readFileInput(file2test)
    val fileInputTest2 = readFileInput(file2test)



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