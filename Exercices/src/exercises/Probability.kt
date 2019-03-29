package exercises

import kotlin.math.absoluteValue

class Probability(private val value: Double) : Comparable<Probability> {
    private val max = 1.0

    private val delta = 0.0000000000001
    init {
        if (value !in (0.0..max))
            throw IllegalArgumentException("Should be between 0 and 1")
    }

//    companion object {
//
//        fun greatestOf(probabilities: Array<Probability>): Probability {
//            if (probabilities.isEmpty()) throw IllegalArgumentException("Array is empty")
//
//            return probabilities.reduce { champion, challenger ->
//                if (champion > challenger) champion else challenger
//            }
//        }
//    }

    override fun isGreaterThan(other: Probability) = this.value > other.value

    override fun equals(other: Any?) =
            this === other || other is Probability && (this.value - other.value).absoluteValue <= delta

    override fun hashCode() = value.hashCode()

    operator fun not() = Probability(max - value)

    operator fun compareTo(other: Probability) = this.value.compareTo(other.value)

    fun and(other: Probability) = Probability(this.value * other.value)

    fun or(other: Probability) = !(!this).and(!other) // De Morgan's law // not P(not A) and P(not B)
    //fun or(other: Probability) = Probability(this.value + other.value - this.and(other).value) // P(A) + P(B) - P(A and B)
}