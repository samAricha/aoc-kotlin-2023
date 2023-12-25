package day05


import readInput

fun main() {
    val file1test = "day05/test1"
    val file2test = "day05/test2"
    val file1 = "day05/file1"
    val file2 = "day05/file2"

    val fileInput1 = readInput(file1)
    val fileInput2 = readInput(file2)
    val fileInputTest1:List<String> = readInput(file1test)
    val fileInputTest2 = readInput(file2test)

    //just a value for holding file i'm currently working on
    val currentWorkingInput = fileInput1

    val seedsPart1: List<Long> = parsePart1Seeds(currentWorkingInput)
    val seedsPart2: Set<LongRange> = parsePart2Seeds(currentWorkingInput)

    val ranges: List<Set<RangePair>> = parseRanges(currentWorkingInput)

//    println(solvePart1(seedsPart1, ranges))
    println(solvePart2(seedsPart2, ranges))




}

fun solvePart1(seedsPart1: List<Long>, ranges: List<Set<RangePair>>): Long{
    return seedsPart1.minOf { seed ->
        ranges.fold(seed) { acc, ranges ->
            ranges.firstOrNull { acc in it }?.translate(acc) ?: acc
        }
    }

}

fun solvePart2(seedsPart2:Set<LongRange>, ranges: List<Set<RangePair>>): Long {

    val rangesReversed = ranges.map { range -> range.map { it.flip() } }.reversed()

    return generateSequence(0L, Long::inc).first { location ->
        val seed = rangesReversed.fold(location) { acc, ranges ->
            ranges.firstOrNull { acc in it }?.translate(acc) ?: acc
        }
        seedsPart2.any { seedRange -> seed in seedRange }
    }
}


private fun parsePart1Seeds(input: List<String>): List<Long> =
    input.first().substringAfter(":").trim().split(" ").map { it.toLong() }

fun parsePart2Seeds(input: List<String>): Set<LongRange> =
    input.first().substringAfter(":").trim().split(" ")
        .map { it.toLong() }.chunked(2).map {
            it.first()..<it.first() + it.last()
        }.toSet()

private fun parseRanges(input: List<String>): List<Set<RangePair>> =
    input.drop(2).joinToString("\n").split("\n\n").map {
        it.split("\n").drop(1).map { line -> RangePair.of(line) }.toSet()
    }



data class RangePair(
    private val source: LongRange,
    private val destination: LongRange
) {

    fun translate(num: Long): Long =
        destination.first + (num - source.first)

    operator fun contains(num: Long): Boolean =
        num in source

    companion object {
        fun of(row: String): RangePair {
            val parts = row.split(" ").map { it.toLong() }
            return RangePair(
                parts[1]..<(parts[1] + parts[2]),
                parts[0]..<(parts[0] + parts[2])
            )
        }
    }

    fun flip(): RangePair =
        RangePair(destination, source)
}

