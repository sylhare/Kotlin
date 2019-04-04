package graph

class InvalidPath internal constructor() : Path() {

    override fun cost(): Double = Double.POSITIVE_INFINITY

    override fun hopCount(): Int = Int.MAX_VALUE
}
