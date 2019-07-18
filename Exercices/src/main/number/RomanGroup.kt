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

}

class RomanUni : RomanGroup() {
    override val max = RomanUnit.X
    override val medium = RomanUnit.V
    override val min = RomanUnit.I
}