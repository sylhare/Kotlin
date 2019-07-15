package number


class Roman {

    private val value: Int
    private val visual: String

    private constructor() {
        this.value = 1
        this.visual = "I"
    }

    private constructor(value: Int, roman: Roman, visual: String) {
        this.value = value * roman.value
        this.visual = visual
    }


    companion object {

        fun convertBasedUnit(number: Int, unit: RomanSystem): String {
            if (unit.parse(number).isNotEmpty()) return unit.parse(number)
            if (unit.parse(number + 1).isNotEmpty()) return unit.min.visual + unit.parse(number + 1)
            if (unit.multiParse(number).isNotEmpty()) return unit.multiParse(number)
            return ""
        }

        fun parse(number: Int, roman: Roman) = when (number) {
            roman.value -> roman.visual
            else -> ""
        }

        fun multiParse(number: Int, min: Roman, medium: Roman) = when {
            number > medium.value -> medium.visual + min.visual.repeat(number - medium.value)
            else -> min.visual.repeat(number)
        }

        fun parseM(number: Int) = M.visual.repeat(number)

        val I = Roman()
        val V = Roman(5, I, "V")
        val X = Roman(10, I, "X")
        val L = Roman(5, X, "L")
        val C = Roman(10, X, "C")
        val D = Roman(5, C, "D")
        val M = Roman(10, C, "M")
    }

    override fun equals(other: Any?) = this === other || other is Roman && this.value == other.value
    override fun hashCode() = 31 * value + visual.hashCode()
}

