package exercises

class Metric(value: Number, metrics: Metrics) {

    private val measure: Double = value.toDouble() * metrics.value

    override fun equals(other: Any?) =
        this === other || other is Metric && this.measure  == other.measure

    override fun hashCode() = measure.toInt()

    operator fun plus(other: Metric) = Metric(other.measure + measure, Metrics.TEASPOON)
}