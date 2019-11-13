package input

class Anagram {

    companion object {
        internal fun filter(listOfWords: List<String>) = listOfWords
            .map { it.toCharArray().sorted().joinToString(separator = "") }.toSet()
    }
}