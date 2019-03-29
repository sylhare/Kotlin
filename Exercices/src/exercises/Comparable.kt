package exercises

interface Comparable<T> {

    companion object {
        fun <S:Comparable<S>> greatestOf(array: Array<S>): S? {
            if (array.isEmpty()) return null
            return array.reduce { champion, challenger ->
                if (champion.isGreaterThan(challenger)) champion else challenger
            }
        }
    }

    fun isGreaterThan(other: T): Boolean

}

fun <S:Comparable<S>> Array<S>.best(): S? {
    if (this.isEmpty()) return null
    return this.reduce { champion, challenger ->
        if (champion.isGreaterThan(challenger)) champion else challenger
    }
}