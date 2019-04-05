package graph

class Path internal constructor() {

    private var connexions = mutableListOf<Connexion>()

    fun cost() = connexions.totalCost()

    fun hopCount() = connexions.size

    fun prepend(connexion: Connexion) = connexions.add(0, connexion)
}

// Short function that takes a double and return a double, a name for a function signature
internal typealias CostStrategy = (Path) -> Number