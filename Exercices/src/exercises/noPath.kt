package exercises

class NoPath private constructor(): Path {

    companion object {
        val noPath = NoPath()
    }

    override fun cost(): Double = Double.POSITIVE_INFINITY

    override fun hopCount(): Int = Double.POSITIVE_INFINITY.toInt()

    override fun prepend(connexion: Connexion) {/* Do Nothing, prevents memory leak */}
}
