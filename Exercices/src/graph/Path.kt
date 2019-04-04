package graph

abstract class Path internal constructor() {

    abstract fun cost(): Double
    abstract fun hopCount(): Int
    internal open fun prepend(connexion: Connexion) { /* Do nothing */
    }

    companion object {
        val invalid = InvalidPath()
    }
}

// Short function that takes a double and return a double, a name for a function signature
internal typealias CostStrategy = (Path) -> Number