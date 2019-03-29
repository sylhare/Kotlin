package exercises

interface Comparable<T> {

    companion object {
        fun <S:Comparable<S>>greatestOf(array: Array<S>): S? {
            if (array.isEmpty()) return null //throw IllegalArgumentException("Array is empty")
            return array.reduce { champion, challenger ->
                if (champion.isGreaterThan(challenger)) champion else challenger
            }
        }
    }

    fun isGreaterThan(other: T): Boolean

}