package graph

import graph.Connexion.Companion.fewestHops
import graph.Connexion.Companion.leastCost
import graph.NoPath.Companion.noPath


class Node {
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

    infix fun path(destination: Node): Path {
        return path(destination, mutableListOf()).apply {
            if (this == noPath) throw IllegalArgumentException("Can't reach")
        }
    }

    internal fun path(destination: Node, visitedNodes: List<Node>): Path {
        if (this === destination) return ValidPath()
        if (this in visitedNodes) return noPath

        return connexions
                .map { it.path(destination, visitedNodes + this) }.min()
    }
}