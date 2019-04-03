package exercises

class Connexion(target: Node, cost: Double) {
    private val node = target
    private val cost = cost

    companion object strategy {
        internal val leastCost = { cost: Double -> cost }
        internal val fewestHops = { _: Double -> 1.0 }
    }

    fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        return node.cost(destination, visitedNodes, strategy) + strategy(cost)
    }
}

// Short function that takes a double and return a double, a name for a function signature
internal typealias CostStrategy = (Double) -> Double
