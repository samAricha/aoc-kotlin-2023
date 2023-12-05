import java.lang.StringBuilder

fun main() {

    val y = extractSpelledNumbers("xtwone3four")
    println("replaced: $y")

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






