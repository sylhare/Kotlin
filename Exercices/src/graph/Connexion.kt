package graph

class Connexion(private val node: Node, private val cost: Double) {

    internal companion object {
        fun totalCost(connexions: List<Connexion>) = if (connexions.isEmpty()) 0.0 else connexions.sumByDouble { it.cost } //Could be in Unit()
    }

    fun paths(visitedNodes: List<Node>) = node.paths(visitedNodes).onEach { it prepend this }
}

// Extension method to List<Connexion> that call static method inside connexion
internal fun List<Connexion>.totalCost() = Connexion.totalCost(this)

