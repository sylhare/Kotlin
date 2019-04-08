package main.kotlin.quantity

// Quantity - Analysis pattern by Martin Fowler

class RatioQuantity internal constructor(amount: Number, private val unit: Unit) : IntervalQuantity(amount, unit) {


    operator fun plus(other: RatioQuantity): RatioQuantity = RatioQuantity(this.amount + convertedAmount(other), this.unit)
    operator fun unaryPlus() = this
    operator fun minus(other: RatioQuantity) = this + - other
    operator fun unaryMinus() = RatioQuantity(-amount, unit)
}
