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
    }

    fun ratio(unit: Unit) = this.baseUnit.toDouble() / unit.baseUnit.toDouble()

    internal fun hashCode(amount: Double) = (baseUnit * amount).hashCode()

    fun isCompatibleWith(other: Unit)= other.type == this.type
}

val Number.teaspoons get() = Quantity(this, Unit.teaspoon)
//val Number.tablespoons get() = Quantity(this, Unit.tablespoon)
//val Number.ounces get() = Quantity(this, Unit.ounce)
//val Number.cups get() = Quantity(this, Unit.cup)
//val Number.pints get() = Quantity(this, Unit.pint)
//val Number.quarts get() = Quantity(this, Unit.quart)
//val Number.gallon get() = Quantity(this, Unit.gallon)
