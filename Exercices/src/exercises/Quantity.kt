package exercises

import kotlin.math.absoluteValue

// Quantity - Analysis pattern by Martin Fowler

class Quantity(amount: Number, private val unit: Unit) {

    private val amount = amount.toDouble()
    private val delta = 0.1

    private fun convertedAmount(other: Quantity) = this.unit.convertedAmount(other.amount, other.unit)

    operator fun plus(other: Quantity) = Quantity(this.amount + convertedAmount(other), this.unit)
    operator fun unaryPlus() = this
    operator fun minus(other: Quantity) = this + - other
    operator fun unaryMinus() = Quantity(- amount, unit)

    override fun equals(other: Any?) =
            this === other ||
                    other is Quantity && this.unit.isCompatibleWith(other.unit) &&
                    (convertedAmount(other) - this.amount).absoluteValue < delta

    override fun hashCode() = unit.hashCode(this.amount)
    override fun toString(): String {
        return "Quantity(unit=$unit, amount=$amount, delta=$delta)"
    }
}
