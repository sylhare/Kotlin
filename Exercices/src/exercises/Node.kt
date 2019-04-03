package exercises

import exercises.Connexion.strategy.fewestHops
import exercises.Connexion.strategy.leastCost


class Node internal constructor() {
    private val connexions = mutableListOf<Connexion>()
    private val unreachable = Double.POSITIVE_INFINITY

    infix fun canReach(destination: Node) = cost(destination, mutableListOf(), fewestHops) != unreachable

    infix fun hops(destination: Node): Int = cost(destination, fewestHops).toInt()

    infix fun cost(destination: Node): Double = cost(destination, leastCost)

    private fun cost(destination: Node, strategy: CostStrategy): Double {
        return cost(destination, mutableListOf(), strategy).apply {
            if (this == unreachable) throw IllegalArgumentException("Can't reach")
        }
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        if (this === destination) return 0.0
        if (this in visitedNodes) return unreachable

        return connexions
                .map { it.cost(destination, visitedNodes + this, strategy) }
                .min() ?: unreachable
    }

    infix fun cost(amount: Number): ConnexionBuilder {
        return ConnexionBuilder(amount.toDouble(), connexions)
    }

}
