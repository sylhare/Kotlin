package exercises

interface Path {

    fun cost(): Double
    fun hopCount(): Int
    fun prepend(connexion: Connexion)

    companion object {
        fun min(paths: List<Path>): Path {
            if (paths.isEmpty()) return NoPath.noPath
            return paths.reduce { champion, challenger -> if (challenger.cost() < champion.cost()) challenger else champion }
        }
    }
}

internal fun List<Path>.min() = Path.min(this)