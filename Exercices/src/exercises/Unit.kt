package exercises

class Unit {
    private val baseUnit: Int
    private val type: Any

    private constructor() {
        this.type = this
        this.baseUnit = 1
    }

    private constructor(value: Int, unit: Unit) {
        this.type = unit.type
        this.baseUnit = value * unit.baseUnit
    }

    companion object {
        val teaspoon = Unit()
        val tablespoon = Unit(3, teaspoon)
        val ounce = Unit(2, tablespoon)
        val cup = Unit(8, ounce)
        val pint = Unit(2, cup)
        val quart = Unit(2, pint)
        val gallon = Unit(4, quart)

        val inch = Unit()
        val foot = Unit(12, inch)
        val yard = Unit(3, foot)
        val chain = Unit(22, yard)
        val furlong = Unit(10, chain)
        val mile = Unit(8, furlong)
    }

    fun ratio(unit: Unit) = this.baseUnit.toDouble() / unit.baseUnit.toDouble()

    internal fun hashCode(amount: Double) = (baseUnit * amount).hashCode()

    fun isCompatibleWith(other: Unit)= other.type == this.type
}

// Overload Number with getters
