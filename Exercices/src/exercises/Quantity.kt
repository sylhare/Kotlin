package exercises

// Quantity - Analysis pattern by Martin Fowler

import kotlin.math.absoluteValue

class Quantity(amount: Number, private val unit: Unit) {

    private val amount = amount.toDouble()
    private val delta = 0.000000000001

    operator fun plus(other: Quantity) = Quantity(this.amount + convertedAmount(other), this.unit)

    operator fun minus(other: Quantity) = this + - other

    operator fun unaryMinus() = Quantity(- amount, unit)
    operator fun unaryPlus() = this

    private fun convertedAmount(other: Quantity) = other.unit.ratio(this.unit) * other.amount

    override fun equals(other: Any?) =
            this === other ||
                    other is Quantity && this.unit.isCompatibleWith(other.unit) &&
                    (convertedAmount(other) - this.amount).absoluteValue < delta

    override fun hashCode() = unit.hashCode(this.amount)
}
