package exercises

class Unit(value: Number, private val units: Units) {

    private val measure: Double = value.toDouble() * units.value

    override fun equals(other: Any?): Boolean {
        return this === other || other is Unit && this.measure  == other.measure
    }

    operator fun plus(other: Unit) = Unit(other.measure + this.measure, Units.TEASPOON)
}