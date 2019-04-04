package graph

import graph.Path.Companion.fewestHops
import graph.Path.Companion.leastCost


class Node {
    private val connexions = mutableListOf<Connexion>()

    infix fun canReach(destination: Node) = path(destination, mutableListOf(), fewestHops) != Path.invalid

    infix fun hops(destination: Node): Int = cost(destination, fewestHops).toInt()

    infix fun cost(destination: Node): Double = path(destination).cost()

    private fun cost(destination: Node, strategy: CostStrategy): Double {
        return path(destination, mutableListOf(), strategy).apply {
            if (this == Path.invalid) throw IllegalArgumentException("Can't reach")
        }.cost(strategy)
    }

    infix fun cost(amount: Number): ConnexionBuilder {
        return ConnexionBuilder(amount.toDouble(), connexions)
    }

    infix fun path(destination: Node): Path {
        return path(destination, mutableListOf(), strategy = leastCost).apply {
            if (this == Path.invalid) throw IllegalArgumentException("Can't reach")
        }
    }

    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Path {
        if (this === destination) return ValidPath()
        if (this in visitedNodes) return Path.invalid

        return connexions
                .map { it.path(destination, visitedNodes + this, strategy) } //.min()
                .minBy{ it.cost(strategy) } ?: Path.invalid
    }
}