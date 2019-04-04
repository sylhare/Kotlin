package graph

abstract class Path internal constructor(){

    abstract fun cost(): Double
    abstract fun hopCount(): Int
    internal open fun prepend(connexion: Connexion) { /* Do nothing */ }

    companion object {
        fun min(paths: List<Path>): Path {
            if (paths.isEmpty()) return invalid
            return paths.reduce { champion, challenger -> if (challenger.cost() < champion.cost()) challenger else champion }
        }
        val invalid = InvalidPath()
    }
}

internal fun List<Path>.min() = Path.min(this)