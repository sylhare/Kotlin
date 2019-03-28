package exercises

class Interval(amount: Number, unit: Unit) : Quantity(amount, unit) {

    override operator fun plus(other: Quantity): Quantity = throw IllegalArgumentException(" You can't add intervals")
}