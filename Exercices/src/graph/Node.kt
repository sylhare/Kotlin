package graph


class Node {
    private val connexions = mutableListOf<Connexion>()

    infix fun canReach(destination: Node) = paths(destination).isNotEmpty()

    infix fun hops(destination: Node): Int = path(destination, Path::hopCount).hopCount()

    infix fun cost(destination: Node): Double = path(destination, Path::cost).cost()

    infix fun path(destination: Node): Path = path(destination, Path::cost)

    infix fun paths(destination: Node): List<Path> = paths(destination, mutableListOf())

    infix fun cost(amount: Number): ConnexionBuilder = ConnexionBuilder(amount.toDouble(), connexions)

    private fun path(destination: Node, strategy: CostStrategy) =
            paths(destination).minBy { strategy(it).toDouble() }
            ?: throw IllegalArgumentException("Can't reach")

    internal fun paths(destination: Node, visitedNodes: List<Node>): List<Path> {
        if (this === destination) return listOf(Path())
        if (this in visitedNodes) return emptyList()

        return connexions
                .flatMap { it.paths(destination, visitedNodes + this) }    // tranform a list of list of stuff into a simple list of stuff
    }
}