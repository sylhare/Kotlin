package exercises

class Probability(private val fraction: Double) {
    init {
        if (fraction < 0 || fraction > 1.0)
            throw IllegalArgumentException("Should be between 0 and 1")
    }

    override fun equals(other: Any?) =
            this === other || other is Probability && this.fraction == other.fraction


    override fun hashCode() = fraction.hashCode()
}