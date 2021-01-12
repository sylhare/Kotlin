package graph

class Path internal constructor() {

    private var connections = mutableListOf<Connection>()
    private var nodes = mutableListOf<Node>()

    internal companion object {
        internal fun filterBy(paths: List<Path>, destination: Node) = paths.filter { destination == it.nodes.last() }
    }

    fun cost() = connections.totalCost()
    fun hopCount() = connections.size

    infix fun prepend(connection: Connection) = connections.add(0, connection)
    infix fun prepend(node: Node) = nodes.add(0, node)
}

internal fun List<Path>.filterBy(destination: Node) = Path.filterBy(this, destination)
internal typealias CostStrategy = (Path) -> Number // Short function that takes a double and return a double, a name for a function signature
