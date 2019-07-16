package number

abstract class RomanGroup {
    abstract val max: RomanUnit
    abstract val medium: RomanUnit
    abstract val min: RomanUnit

    open fun parse(number: Int): String {
        if (number == 0) return ""
        return RomanUnit.singleParse(number, max) + RomanUnit.singleParse(number, medium) + RomanUnit.singleParse(number, min)
    }

    open fun multiParse(number: Int) = RomanUnit.multiParse(number, min, medium)
    fun numberToList(n: Int) = n.toString().reversed().map { it.toInt() }
}

class RomanUni : RomanGroup() {
    override val max = RomanUnit.X
    override val medium = RomanUnit.V
    override val min = RomanUnit.I
}

class RomanDeci : RomanGroup() {
    override val max = RomanUnit.C
    override val medium = RomanUnit.L
    override val min = RomanUnit.X
}

class RomanCenti : RomanGroup() {
    override val max = RomanUnit.M
    override val medium = RomanUnit.D
    override val min = RomanUnit.C
}

class RomanMaxi : RomanGroup() {
    override val max = RomanUnit.M
    override val medium = RomanUnit.M
    override val min = RomanUnit.M

    override fun multiParse(number: Int) = this.parse(number)
    override fun parse(number: Int) = if (number == 0) "" else RomanUnit.parseM(number)
}