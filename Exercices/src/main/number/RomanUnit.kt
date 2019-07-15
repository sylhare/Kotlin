package number

abstract class RomanSystem {
    abstract val max: Roman
    abstract val medium: Roman
    abstract val min: Roman

    open fun parse(number: Int): String {
        if (number == 0) return ""
        return Roman.parse(number, max) + Roman.parse(number, medium) + Roman.parse(number, min)
    }

    open fun multiParse(number: Int) = Roman.multiParse(number, min, medium)

}

class RomanUnit : RomanSystem() {
    override val max = Roman.X
    override val medium = Roman.V
    override val min = Roman.I
}

class RomanDeci : RomanSystem() {
    override val max = Roman.C
    override val medium = Roman.L
    override val min = Roman.X
}

class RomanCenti : RomanSystem() {
    override val max = Roman.M
    override val medium = Roman.D
    override val min = Roman.C
}

class RomanMax : RomanSystem() {
    override val max = Roman.M
    override val medium = Roman.M
    override val min = Roman.M

    override fun multiParse(number: Int) = this.parse(number)
    override fun parse(number: Int): String {
        if (number == 0) return ""
        return Roman.parseM(number)
    }
}