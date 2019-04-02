package exercises

class Node internal constructor() {
    private val siblings = mutableListOf<Node>()
    private val unreachable = Double.POSITIVE_INFINITY

    infix fun to(other: Node): Node {
        siblings.add(other)
        return other
    }

    infix fun canReach(other: Node): Boolean {
        return canReach(other, mutableListOf())
    }

    private fun canReach(other: Node, visitedNodes: MutableList<Node>): Boolean {
        if (this === other) return true
        if (this in visitedNodes) return false
        visitedNodes.add(this)

        return siblings.any { it.canReach(other, visitedNodes) }
    }

    infix fun hopsCount(destination: Node): Int = hopsCount(destination, mutableListOf()).apply {
         if (this == unreachable) throw IllegalArgumentException("Can't reach")
    }.toInt()

    fun hopsCount(destination: Node, visitedNodes: List<Node>): Double {
        if (this === destination) return 0.0
        if (this in visitedNodes) return unreachable

        siblings.map { it.hopsCount(destination, visitedNodes.plus(this)) }
                .filter { it != unreachable }
                .apply { return if (this.isEmpty()) unreachable else this.min()!! + 1}
    }

}