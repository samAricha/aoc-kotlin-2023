package day01

fun main() {

    val inputList = listOf(
        "two1nine",
        "eightwothree",
        "abcone2threexyz",
        "xtwone3four",
        "4nineeightseven2",
        "zoneight234",
        "7pqrstsixteen",
        "mtfvjbbpdeight1"
    )

    val resultList = processStringList(inputList)
    val resultListv2 = processStringListv2(inputList)
    println(resultListv2)
//    resultList.forEachIndexed { index, result ->
//        println("Input: ${inputList[index]}, Result: $result")
//    }

//    val y = extractSpelledNumbers("xtwone")
//    println("replaced: $y")

//    val result = replaceDigitsIfAny("xxtwo")
//    println("final = $result")

}

fun processStringList(inputList: List<String>): List<String> {
    return inputList.map { extractSpelledNumbers(it) }
}

fun processStringListv2(inputList: List<String>): List<String> {
    return inputList.map { replaceDigitsIfAny(it) }
}

val digitWords = mapOf(
    "zero" to '0',
    "one" to '1',
    "two" to '2',
    "three" to '3',
    "four" to '4',
    "five" to '5',
    "six" to '6',
    "seven" to '7',
    "eight" to '8',
    "nine" to '9'
)


fun extractSpelledNumbers(input: String): String {
    val currentWord = StringBuilder()
    val uniResult = StringBuilder()

    for (char in input) {
        val qualifiedDigitWords = digitWords.keys.filter { currentWord.toString().contains(it) }
        if (qualifiedDigitWords.isNotEmpty()) {
            var ourCurrentWord = currentWord.toString()
            for (word in qualifiedDigitWords) {
                ourCurrentWord = ourCurrentWord.replace(word, digitWords[word].toString())
            }
            uniResult.append(ourCurrentWord)
            currentWord.clear()
        }

        if (char.isDigit()) {
            uniResult.append(char)
            currentWord.clear()
        } else if (char.isLetter()) {
            currentWord.append(char)
        }
    }

    // Check and convert the last word if it's a qualified digit word
    val lastWord = currentWord.toString()
    val qualifiedLastWord = digitWords.keys.find { lastWord.contains(it) }
    if (qualifiedLastWord != null) {
        uniResult.append(lastWord.replace(qualifiedLastWord, digitWords[qualifiedLastWord].toString()))
    } else {
        uniResult.append(lastWord)
    }

    return uniResult.toString()
}





fun replaceDigitsIfAny(input: String): String {
    var result = input

    digitWords.forEach { (word, digit) ->
        result = result.replace(word, digit.toString())
    }

    return result
}



