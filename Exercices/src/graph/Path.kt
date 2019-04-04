package graph

abstract class Path internal constructor(){

    abstract fun cost(strategy: CostStrategy = leastCost): Double
    abstract fun hopCount(): Int
    internal open fun prepend(connexion: Connexion) { /* Do nothing */ }

    companion object {
/*        fun min(paths: List<Path>): Path {
            if (paths.isEmpty()) return invalid
            return paths.reduce { champion, challenger -> if (challenger.cost(leastCost) < champion.cost(leastCost)) challenger else champion }
        }*/
        internal val leastCost = { cost: Double -> cost }
        internal val fewestHops: CostStrategy = { 1.0 }
        val invalid = InvalidPath()
    }
}

//internal fun List<Path>.min() = Path.min(this)

// Short function that takes a double and return a double, a name for a function signature
internal typealias CostStrategy = (Double) -> Double