package graph

class Connexion(private val node: Node, private val cost: Double) {

    internal companion object {
        fun totalCost(connexions: List<Connexion>) = connexions.sumByDouble { it.cost }  //sumByDouble <=> map{}.sum{}
    }

    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Path {
        return node.path(destination, visitedNodes, strategy).apply { this.prepend(this@Connexion) }  //this is the object you are applying, this@Connexion is the this of the object
    }

    fun paths(destination: Node, visitedNodes: List<Node>): List<Path> {
        return node.paths(destination, visitedNodes).onEach { it.prepend(this@Connexion) }  //onEach is a .map{ this.apply { ... } }
    }
}

// Extension method to List<Connexion> that call static method inside connexion
internal fun List<Connexion>.totalCost() = Connexion.totalCost(this)

