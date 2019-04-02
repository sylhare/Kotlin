package exercises

class Node internal constructor() {
    private val siblings = mutableListOf<Node>()
    private val unreachable = Int.MAX_VALUE

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

    fun hopsCount(destination: Node): Int {
        return hopsCount(destination, mutableListOf()).apply {
             if (this == unreachable) throw IllegalArgumentException("Can't reach")
        }
    }

    fun hopsCount(destination: Node, visitedNodes: List<Node>): Int {
        if (this === destination) return 0
        if (this in visitedNodes) return unreachable

        siblings.map { it.hopsCount(destination, visitedNodes.plus(this)) }
                .filter { it != unreachable }
                .apply { return if (this.isEmpty()) unreachable else this.min()!! + 1}
    }

}