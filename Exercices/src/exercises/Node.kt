package exercises

class Node() {
    private val siblings = mutableListOf<Node>()

    infix fun to(other: Node) {
        siblings.add(other)
    }

    infix fun canReach(other: Node): Boolean {
        return canReach(other, mutableListOf())
    }

    private fun canReach(other: Node, visitedNodes: MutableList<Node>): Boolean {
        if (this === other) return true
        if (this in visitedNodes) return false
        visitedNodes.add(this)

        return siblings.any{ it.canReach(other, visitedNodes) }
    }
}