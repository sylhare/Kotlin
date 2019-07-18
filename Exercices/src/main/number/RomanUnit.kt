package number


class RomanUnit {

    private val value: Int
    private val visual: String

    private constructor() {
        this.value = 1
        this.visual = "I"
    }

    private constructor(value: Int, romanUnit: RomanUnit, visual: String) {
        this.value = value * romanUnit.value
        this.visual = visual
    }

    companion object {

        fun convertBasedUnit(number: Int, group: RomanGroup): String {
            if (group.parse(number).isNotEmpty()) return group.parse(number)  // 1, 5, 10
            if (group.parse(number + 1).isNotEmpty()) return group.min.visual + group.parse(number + 1) // 4, 9
            if (group.multiParse(number).isNotEmpty()) return group.multiParse(number) // 2, 3, 6
            return ""
        }

        fun singleParse(number: Int, romanUnit: RomanUnit) = if (number == romanUnit.value) romanUnit.visual else ""

        fun multiParse(number: Int, min: RomanUnit, medium: RomanUnit) = when {
            number > medium.value -> medium.visual + min.visual.repeat(number - medium.value)
            else -> min.visual.repeat(number)
        }

        val I = RomanUnit()
        val V = RomanUnit(5, I, "V")
        val X = RomanUnit(10, I, "X")
        val L = RomanUnit(5, X, "L")
        val C = RomanUnit(10, X, "C")
        val D = RomanUnit(5, C, "D")
        val M = RomanUnit(10, C, "M")
    }

    override fun equals(other: Any?) = this === other || other is RomanUnit && this.value == other.value
    override fun hashCode() = 31 * value + visual.hashCode()
}
