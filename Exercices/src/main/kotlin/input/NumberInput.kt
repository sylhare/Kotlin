package input

data class NumberInput(private val digits: String) {

    companion object {
        internal fun List<NumberInput>.sortConcatenate() = this.sortedBy { it.digits }
            .joinToString(separator = "") { it.digits }.takeWhile { it.isDigit() }

        internal fun List<NumberInput>.has(char: Char) = !this.none { it.digits.contains(char) }

        internal fun  List<Number>.toNumberInput() = this.map { it.input }
        val Number.input get() = NumberInput(this.toString())

        fun intToList(n: Int) = n.toString().map { Character.getNumericValue(it) }
    }
}