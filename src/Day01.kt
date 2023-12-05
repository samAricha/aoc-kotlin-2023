import java.lang.StringBuilder

fun main() {

    val testFile1 = "day_01_test"
    val originalFile = "day_01_test"
    val testFile2 = "day_01_test2"
    val fileInput = readFileInput(testFile1)
    val fileInput2 = readFileInput(testFile2)
//    fun1(fileInput)
//    fun2(fileInput2)
    val x = replaceSpelledOutDigits("twoeighthree")
    val y = extractNumbers("xtwone3four")
    println("replaced: $y")

}

fun fun1(fileInput: String) : Int?{
    val parsedInput = parseInput(fileInput)
    val extractedDigits:List<Int> = parsedInput.mapNotNull { extractAndCombine(it) }
    val totalSum: Int = extractedDigits.sum()
    println(totalSum)
    return totalSum
}

fun fun2(fileInput: String) : Int?{
    val parsedInput = parseInput(fileInput)
    val extractedDigits:List<Int> = parsedInput.mapNotNull { modifiedExtractAndCombine(it) }
    val totalSum: Int = extractedDigits.sum()
    println(totalSum)
    return totalSum
}
val textToDigits = mapOf(
    "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
    "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
)
fun parseInput(input: String):List<String> = input.split("\n")

fun modifiedExtractAndCombine(input: String): Int?{

    val modifiedInput = replaceSpelledOutDigits(input)
    println("Modified input $modifiedInput")


    val digits = StringBuilder()

    for(char in modifiedInput){
        if (char.isDigit()){
            digits.append(char)
        }
    }

    if (digits.isNotEmpty()){
        val firstDigit = digits[0].toString().toInt()
        val lastDigit = digits[digits.length -1].toString().toInt()
        return "$firstDigit$lastDigit".toInt()
    }

    return null
}


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
        return "$firstDigit$lastDigit".toInt()
    }

    return null
}

fun replaceSpelledOutDigits(input: String): String {
    val spelledOutDigits = mapOf(
        "one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
        "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9"
    )

    return spelledOutDigits.entries.fold(input) { acc, (key, value) ->
        acc.replace(Regex("\\b$key\\b"), value)
    }
}


fun extractNumbers(input: String): String {
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

    val result = StringBuilder()
    val currentWord = StringBuilder()

    for (char in input) {
        if (char.isLetter()) {
            currentWord.append(char)
        } else {
            val digit = digitWords[currentWord.toString()]
            if (digit != null) {
                result.append(digit)
                currentWord.clear()
            } else {
                result.append(currentWord)
                currentWord.clear()
            }
            result.append(char)
        }
    }

    val digit = digitWords[currentWord.toString()]
    if (digit != null) {
        result.append(digit)
    } else {
        result.append(currentWord)
    }

    return result.toString()
}






