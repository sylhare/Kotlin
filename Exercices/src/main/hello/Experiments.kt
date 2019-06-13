package hello

data class Experiments(private val digit: String) : Comparable<Experiments> {

    override fun compareTo(other: Experiments) = this.digit.compareTo(other.digit)

    companion object {
        internal fun concatenate(keys: List<Experiments>) = keys.sorted()
                .joinToString(separator = "") { it.digit }.takeWhile { it.isDigit() }

        internal fun has(keys: List<Experiments>, char: Char) = !keys.none { it.digit.contains(char) }
    }

}