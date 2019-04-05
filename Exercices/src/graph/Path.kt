package graph

class Path internal constructor() {

    private var connexions = mutableListOf<Connexion>()
    private var nodes = mutableListOf<Node>()

    internal companion object {
        internal fun filterBy(paths: List<Path>, destination: Node) = paths.filter { destination == it.nodes.last() }
    }

    fun cost() = connexions.totalCost()

    fun hopCount() = connexions.size

    infix fun prepend(connexion: Connexion) = connexions.add(0, connexion)
    infix fun prepend(node: Node) = nodes.add(0, node)
}

internal fun List<Path>.filterBy(destination: Node) = Path.filterBy(this, destination)
internal typealias CostStrategy = (Path) -> Number // Short function that takes a double and return a double, a name for a function signature
