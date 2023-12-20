package day02

import readInput

fun main() {
    val file1test = "day02/day_02_test1"
    val file2test = "day02/day_02_test2"

    val file1 = "day02/day_02_1"
    val file2 = "day02/day_02_2"



    val fileInput1 = readInput(file1)
    val fileInput2 = readInput(file2)
    val fileInputTest1:List<String> = readInput(file1test)
    val fileInputTest2 = readInput(file2test)

    //just a value for holding file i'm currently working on
    val currentWorkingInput = fileInput2

    println(currentWorkingInput)
//    val parsedInput = parseInput(currentWorkingInput)
//    println(parsedInput)


    val games: List<GameInfoDataClass> = currentWorkingInput.map { GameInfoDataClass.getCleanGameDataClass(it) }
//    println(solvePart1(games))
    println(solvePart2(games))



}

fun solvePart1(games: List<GameInfoDataClass>): Int{
    val sum:Int = games.filter {
        it.isPossible(12, 13, 14)
    }.sumOf { it.id }

    return sum
}

fun solvePart2(games: List<GameInfoDataClass>): Int{
    val sum = games.sumOf { it.power() }
    return sum
}


data class GameInfoDataClass(
    val id: Int,
    val red: Int,
    val green: Int,
    val blue: Int
) {

    fun isPossible(red: Int, green: Int, blue: Int) =
        this.red <= red && this.green <= green && this.blue <= blue

    fun power() =
        red * blue * green

    companion object{
        fun getCleanGameDataClass(input: String): GameInfoDataClass {
            val gameId = input.substringAfter(" ").substringBefore(":").toInt()
            val colors = mutableMapOf<String, Int>()

            input.substringAfter(":").split(";").forEach{turn ->
                turn.split(",").map { it.trim() }.forEach { draw ->
                    val drawNum = draw.substringBefore(" ").toInt()
                    val drawColor = draw.substringAfter(" ")

                    colors[drawColor] = maxOf(drawNum, colors[drawColor] ?: drawNum)

                }
            }

            return GameInfoDataClass(gameId, colors["red"] ?: 0, colors["green"] ?: 0, colors["blue"] ?: 0)

        }
    }
}
