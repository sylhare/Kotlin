package exercises

class Path internal constructor() {
    private var connexions = mutableListOf<Connexion>()

    fun cost() = connexions.totalCost()

    fun hopCount() = connexions.size

    fun prepend(connexion: Connexion) = connexions.add(0, connexion)
}