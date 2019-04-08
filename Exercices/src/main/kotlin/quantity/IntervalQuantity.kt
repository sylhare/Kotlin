package quantity

import kotlin.math.absoluteValue

open class IntervalQuantity internal constructor(amount: Number, private val unit: Unit) {

    internal val amount = amount.toDouble()
    private val delta = 0.0000000001

    internal open fun convertedAmount(other: IntervalQuantity) = this.unit.convertedAmount(other.amount, other.unit)

    override fun equals(other: Any?) =
            this === other ||
                    other is IntervalQuantity && this.unit isCompatibleWith other.unit &&
                    (convertedAmount(other) - this.amount).absoluteValue < delta

    override fun hashCode() = unit.hashCode(this.amount)
    override fun toString(): String {
        return "Quantity(unit=$unit, amount=$amount, delta=$delta)"
    }

}