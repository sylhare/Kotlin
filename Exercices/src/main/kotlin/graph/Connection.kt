package graph

class Connection(private val node: Node, private val cost: Double) {

    internal companion object {
        fun totalCost(connections: List<Connection>) = if (connections.isEmpty()) 0.0 else connections.sumByDouble { it.cost } //Could be in Unit()
    }

    fun paths(visitedNodes: List<Node>) = node.paths(visitedNodes).onEach { it prepend this }
}

// Extension method to List<Connexion> that call static method inside connexion
internal fun List<Connection>.totalCost() = Connection.totalCost(this)

