package input

sealed class Anagram {

    companion object {
        internal fun filter(listOfWords: List<String>) = listOfWords.map { it.alphaSort() }.toSet()
    }

}

internal fun String.alphaSort() = this.toCharArray().sorted().joinToString(separator = "")
internal fun String.isAnagram(other: String) = this.alphaSort() == other.alphaSort()
internal fun List<String>.removeAllAnagrams() = this.filter { that -> this.map { that.isAnagram(it) }.filter { it }.size == 1 }
internal fun List<String>.removeAnagramDuplicates() = this.associateBy ({it.alphaSort()}, {it}).values.toList()
internal fun List<String>.filterAnagrams() = this.reversed().removeAnagramDuplicates().reversed()
