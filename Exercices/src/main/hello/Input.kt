package hello

data class Input(private val digit: String) : Comparable<Input> {

    override fun compareTo(other: Input) = this.digit.compareTo(other.digit)

    companion object {
        internal fun concatenate(keys: List<Input>) = keys.sorted()
                .joinToString(separator = "") { it.digit }.takeWhile { it.isDigit() }

        internal fun has(keys: List<Input>, char: Char) = !keys.none { it.digit.contains(char) }
        fun numberToList(n: Int) = n.toString().reversed().map { it.toString().toInt() }
    }

}