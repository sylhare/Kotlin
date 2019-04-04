package graph

class InvalidPath internal constructor(): Path() {

    override fun cost(): Double = Double.POSITIVE_INFINITY

    override fun hopCount(): Int = Double.POSITIVE_INFINITY.toInt()
}
