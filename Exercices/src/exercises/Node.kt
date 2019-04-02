package exercises

class Node internal constructor() {
    private val siblings = mutableListOf<Node>()
    private val unreachable = Double.POSITIVE_INFINITY

    infix fun to(other: Node): Node {
        siblings.add(other)
        return other
    }

    infix fun canReach(destination: Node) = hops(destination, mutableListOf()) != unreachable

    infix fun hops(destination: Node): Int = hops(destination, mutableListOf()).apply {
         if (this == unreachable) throw IllegalArgumentException("Can't reach")
    }.toInt()

    fun hops(destination: Node, visitedNodes: List<Node>): Double {
        if (this === destination) return 0.0
        if (this in visitedNodes) return unreachable

        return siblings.map { it.hops(destination, visitedNodes + this) + 1 }.min() ?: unreachable
    }

}