package graph


class Node {
    val paths get() = paths()
    private val connections = mutableListOf<Connection>()

    infix fun canReach(destination: Node) = paths(destination).isNotEmpty()
    infix fun hops(destination: Node) = path(destination, Path::hopCount).hopCount()
    infix fun cost(destination: Node) = path(destination, Path::cost).cost()
    infix fun path(destination: Node) = path(destination, Path::cost)
    infix fun paths(destination: Node) = paths().filterBy(destination)

    infix fun cost(amount: Number): ConnectionBuilder = ConnectionBuilder(amount.toDouble(), connections)

    private fun path(destination: Node, strategy: CostStrategy) =
            paths(destination).minByOrNull { strategy(it).toDouble() } ?: throw IllegalArgumentException("Can't reach")

    private fun paths(): List<Path> = paths(mutableListOf())

    internal fun paths(visitedNodes: List<Node>): List<Path> {
        if (this in visitedNodes) return emptyList()
        return (connections.map { it.paths(visitedNodes + this) }.flatten() + Path()).onEach { it prepend this }
    }
}