package graph

class Connexion(private val node: Node, private val cost: Double) {

    internal companion object {
        fun totalCost(connexions: List<Connexion>) = connexions.sumByDouble { it.cost }  //sumByDouble <=> map{}.sum{}
    }

    fun paths(destination: Node, visitedNodes: List<Node>): List<Path> {
        return node.paths(destination, visitedNodes).onEach { it.prepend(this@Connexion) }  //onEach is a .map{ this.apply { ... } }
    }
}

// Extension method to List<Connexion> that call static method inside connexion
internal fun List<Connexion>.totalCost() = Connexion.totalCost(this)

