package exercises

class Connexion(private val node: Node, private val cost: Double) {

    companion object {
        fun totalCost(connexions: List<Connexion>) = connexions.sumByDouble { it.cost }  //sumByDouble <=> map{}.sum{}
        internal val leastCost = { cost: Double -> cost }
        internal val fewestHops: CostStrategy = { 1.0 }
    }

    fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        return node.cost(destination, visitedNodes, strategy) + strategy(cost)
    }

    fun path(destination: Node, visitedNodes: List<Node>): Path {
        return node.path(destination, visitedNodes).apply { this.prepend(this@Connexion) }  //this is the object you are applying, this@Connexion is the this of the object
    }
}
// Extension method to List<Connexion> that call static method inside connexion
internal fun List<Connexion>.totalCost() = Connexion.totalCost(this)

// Short function that takes a double and return a double, a name for a function signature
internal typealias CostStrategy = (Double) -> Double
