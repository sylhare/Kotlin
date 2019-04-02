package exercises


class Node() {
    private val connexions = mutableListOf<Connexion>()
    private val unreachable = Double.POSITIVE_INFINITY

    infix fun cost(amount: Number): ConnexionBuilder {
        return ConnexionBuilder(amount.toDouble(), connexions)
    }

    infix fun canReach(destination: Node) = hops(destination, mutableListOf()) != unreachable

    infix fun hops(destination: Node): Int = hops(destination, mutableListOf()).apply {
         if (this == unreachable) throw IllegalArgumentException("Can't reach")
    }.toInt()

    fun hops(destination: Node, visitedNodes: List<Node>): Double {
        if (this === destination) return 0.0
        if (this in visitedNodes) return unreachable

        return connexions
                .map { it.hops(destination, visitedNodes + this) }
                .min() ?: unreachable
    }

    infix fun costTo(destination: Node): Double = costTo(destination, mutableListOf<Node>()).apply {
        if (this == unreachable) throw IllegalArgumentException("Can't reach")
    }

    fun costTo(destination: Node, visitedNodes: List<Node>): Double {
        if (this === destination) return 0.0
        if (this in visitedNodes) return unreachable

        return connexions
                .map { it.costTo(destination, visitedNodes + this) }
                .min() ?: unreachable
    }

}
