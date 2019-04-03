package exercises


class Path internal constructor() {
    private var connexions = mutableListOf<Connexion>()

    companion object {
        fun min(paths: List<Path>): Path {
            if (paths.isEmpty()) return invalidPath
            return paths.reduce { champion, challenger -> if (challenger.cost() < champion.cost()) challenger else champion }
        }

        val invalidPath = Path()

    }

    fun cost() = connexions.totalCost()

    fun hopCount() = connexions.size

    fun prepend(connexion: Connexion) = connexions.add(0, connexion)
}

internal fun List<Path>.minPath() = Path.min(this)