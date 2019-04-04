package graph


class Node {
    private val connexions = mutableListOf<Connexion>()

    infix fun canReach(destination: Node) = path(destination) != Path.invalid

    infix fun hops(destination: Node): Int = path(destination, Path::hopCount).hopCount()

    infix fun cost(destination: Node): Double = path(destination, Path::cost).cost()

    private fun path(destination: Node, strategy: CostStrategy): Path {
        return path(destination, mutableListOf(), strategy).apply {
            if (this == Path.invalid) throw IllegalArgumentException("Can't reach")
        }
    }

    infix fun cost(amount: Number): ConnexionBuilder {
        return ConnexionBuilder(amount.toDouble(), connexions)
    }

    infix fun path(destination: Node): Path {
        return path(destination, mutableListOf(), Path::cost).apply {
            if (this == Path.invalid) throw IllegalArgumentException("Can't reach")
        }
    }

    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Path {
        if (this === destination) return ValidPath()
        if (this in visitedNodes) return Path.invalid

        return connexions
                .map { it.path(destination, visitedNodes + this, strategy) }
                .minBy{ strategy(it).toDouble() } ?: Path.invalid
    }
}