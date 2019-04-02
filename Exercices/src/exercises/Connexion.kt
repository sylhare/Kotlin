package exercises

class Connexion(target: Node, cost: Double) {
    private val node = target
    private val cost = cost

    fun costTo(destination: Node, visitedNodes: List<Node>): Double {
        return node.costTo(destination, visitedNodes) + cost
    }

    fun hops(destination: Node, visitedNodes: List<Node>): Double {
        return node.hops(destination, visitedNodes) + 1
    }
}