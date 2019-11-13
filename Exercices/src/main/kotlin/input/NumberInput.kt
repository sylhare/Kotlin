package input

data class NumberInput(private val digit: String) : Comparable<NumberInput> {

    override fun compareTo(other: NumberInput) = this.digit.compareTo(other.digit)

    companion object {
        internal fun concatenate(keys: List<NumberInput>) = keys.sorted()
                .joinToString(separator = "") { it.digit }.takeWhile { it.isDigit() }

        internal fun has(keys: List<NumberInput>, char: Char) = !keys.none { it.digit.contains(char) }
        fun numberToList(n: Int) = n.toString().reversed().map { it.toString().toInt() }
    }

}