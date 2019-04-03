package exercises


class ValidPath internal constructor(): Path {
    private var connexions = mutableListOf<Connexion>()

    override fun cost() = connexions.totalCost()

    override fun hopCount() = connexions.size

    override fun prepend(connexion: Connexion) = connexions.add(0, connexion)
}