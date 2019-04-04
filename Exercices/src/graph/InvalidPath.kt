package graph

class InvalidPath internal constructor(): Path() {

    override fun cost(leastCost: (Double) -> Double): Double = Double.POSITIVE_INFINITY

    override fun hopCount(): Int = Int.MAX_VALUE
}
