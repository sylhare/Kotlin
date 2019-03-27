package exercises

class Unit {
    private val baseUnitRatio: Int = 1
    private val baseUnit: Int

    private constructor() {
        this.baseUnit = baseUnitRatio
    }

    private constructor(value: Int, unit: Unit) {
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
    }

    fun ratio(unit: Unit) = this.baseUnit.toDouble() / unit.baseUnit.toDouble()

    internal fun hashCode(amount: Double) = (baseUnit * amount).hashCode()
}
