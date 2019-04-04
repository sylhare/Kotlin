package graph

class Connexion(private val node: Node, private val cost: Double) {

    companion object {
        fun totalCost(connexions: List<Connexion>) = connexions.sumByDouble { it.cost }  //sumByDouble <=> map{}.sum{}
    }

    fun path(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Path {
        return node.path(destination, visitedNodes, strategy).apply { this.prepend(this@Connexion) }  //this is the object you are applying, this@Connexion is the this of the object
    }
}
// Extension method to List<Connexion> that call static method inside connexion
internal fun List<Connexion>.totalCost() = Connexion.totalCost(this)

