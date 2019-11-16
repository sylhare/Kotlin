package input

class Anagram {

    companion object {
        internal fun filter(listOfWords: List<String>) = listOfWords
            .map { it.toCharArray().sorted().joinToString(separator = "") }.toSet()
    }

}

internal fun String.alphaSort() = this.toCharArray().sorted().joinToString(separator = "")
internal fun String.isAnagram(other: String) = this.alphaSort() == other.alphaSort()
