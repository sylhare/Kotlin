package main.kotlin.exercises

import kotlin.math.absoluteValue

// Could be rewritten with a main.kotlin.graph
class Probability(private val value: Double) : Comparable<Probability> {
    private val max = 1.0

    private val delta = 0.0000000000001

    init {
        if (value !in (0.0..max))
            throw IllegalArgumentException("Should be between 0 and 1")
    }

    override fun equals(other: Any?) =
            this === other || other is Probability && (this.value - other.value).absoluteValue <= delta

    override fun hashCode() = value.hashCode()

    override fun isGreaterThan(other: Probability) = this.value > other.value
    operator fun not() = Probability(max - value)
    operator fun compareTo(other: Probability) = this.value.compareTo(other.value)
    fun and(other: Probability) = Probability(this.value * other.value)
    fun or(other: Probability) = !(!this).and(!other) // De Morgan's law // not P(not A) and P(not B)
    //fun or(other: Probability) = Probability(this.value + other.value - this.and(other).value) // P(A) + P(B) - P(A and B)
}