package main.kotlin.graph


class Node {
    val paths get() = paths()
    private val connexions = mutableListOf<Connexion>()

    infix fun canReach(destination: Node) = paths(destination).isNotEmpty()
    infix fun hops(destination: Node) = path(destination, Path::hopCount).hopCount()
    infix fun cost(destination: Node) = path(destination, Path::cost).cost()
    infix fun path(destination: Node) = path(destination, Path::cost)
    infix fun paths(destination: Node) = paths().filterBy(destination)

    infix fun cost(amount: Number): ConnexionBuilder = ConnexionBuilder(amount.toDouble(), connexions)

    private fun path(destination: Node, strategy: CostStrategy) =
            paths(destination).minBy { strategy(it).toDouble() }
                    ?: throw IllegalArgumentException("Can't reach")

    private fun paths(): List<Path> = paths(mutableListOf())

    internal fun paths(visitedNodes: List<Node>): List<Path> {
        if (this in visitedNodes) return emptyList()
        return (connexions.flatMap { it.paths(visitedNodes + this) } + Path()).onEach { it prepend this }
    }
}