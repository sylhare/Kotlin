package quantity

/*
Type of Unit

Nominal (distance, volume)
Ordinal (scale-like representing a rank)
IntervalQuantity (dates, temperature)
Ratio (days)


 */
internal class Unit {
    private val baseUnitRatio: Double
    private val type: Any
    private val offset: Double

    private constructor() {
        this.type = this
        this.baseUnitRatio = 1.0
        this.offset = 0.0
    }

    private constructor(value: Int, unit: Unit) {
        this.type = unit.type
        this.baseUnitRatio = value * unit.baseUnitRatio
        this.offset = 0.0
    }

    private constructor(value: Double, unit: Unit, offset: Double) {
        this.type = unit.type
        this.offset = offset
        this.baseUnitRatio = value * unit.baseUnitRatio
    }

    internal companion object {
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

        val celsius = Unit()
        val fahrenheit = Unit(5.0 / 9.0, celsius, 32.0)
        val kelvin = Unit(1.0, celsius, 273.15)
        val gasmark = Unit(14.0, celsius, -121.0 / 14.0)
    }

    internal fun hashCode(amount: Double) = (baseUnitRatio * (amount - offset)).hashCode()

    internal fun isCompatibleWith(other: Unit) = other.type == this.type

    internal fun convertedAmount(amount: Number, other: Unit) =
            (amount.toDouble() - other.offset) * other.baseUnitRatio / this.baseUnitRatio + this.offset

}

