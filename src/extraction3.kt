import java.lang.StringBuilder

fun replaceSpelledOutDigits3(input: String): String {
    val spelledOutDigits = mapOf(
        "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
        "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
    )

    val modifiedInput = StringBuilder(input)

    for ((spelledOutDigit, numericValue) in spelledOutDigits.entries) {
        var index = 0
        while (index < modifiedInput.length) {
            // Find the index of the spelled-out digit in the remaining part of modifiedInput
            index = modifiedInput.indexOf(spelledOutDigit, index, ignoreCase = true)
            if (index == -1) {
                break
            }

            // Replace the first occurrence of the spelled-out digit
            modifiedInput.replace(index + 1, index + spelledOutDigit.length, numericValue.toString())
            index += numericValue.toString().length
        }
    }

    return modifiedInput.toString()
}
