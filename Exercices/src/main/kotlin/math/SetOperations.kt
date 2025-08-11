package math

/**
 * A class representing a set of elements with various operations.
 */
class SetOperations<T>(private val elements: MutableSet<T> = mutableSetOf()) {

    /**
     * Adds an element to the set.
     * @param element The element to be added.
     */
    fun add(element: T) {
        elements.add(element)
    }

    /**
     * Removes an element from the set.
     * @param element The element to be removed.
     */
    fun remove(element: T) {
        elements.remove(element)
    }

    /**
     * Checks if the set is empty.
     * @return True if the set is empty, false otherwise.
     */
    fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    /**
     * Returns the cardinality (number of elements) of the set.
     * @return The size of the set.
     */
    fun cardinality(): Int {
        return elements.size
    }

    /**
     * Checks if this set is a subset of another set.
     * @param other The other set to check against.
     * @return True if this set is a subset of the other set, false otherwise.
     */
    fun isSubsetOf(other: SetOperations<T>): Boolean {
        return elements.all { other.elements.contains(it) }
    }

    /**
     * Checks if two sets are equal.
     * @param other The other set to compare with.
     * @return True if both sets contain the same elements, false otherwise.
     */
    fun isEqualTo(other: SetOperations<T>): Boolean {
        return elements == other.elements
    }

    /**
     * Performs the union of this set with another set.
     * @param other The other set to union with.
     * @return A new set containing elements from both sets.
     */
    operator fun plus(other: SetOperations<T>): SetOperations<T> {
        val union = SetOperations<T>(elements.toMutableSet())
        union.elements.addAll(other.elements)
        return union
    }

    /**
     * Performs the intersection of this set with another set.
     * @param other The other set to intersect with.
     * @return A new set containing elements common to both sets.
     */
    operator fun minus(other: SetOperations<T>): SetOperations<T> {
        val intersection = SetOperations<T>()
        for (element in elements) {
            if (other.elements.contains(element)) {
                intersection.add(element)
            }
        }
        return intersection
    }

    /**
     * Performs the difference of this set with another set.
     * @param other The other set to subtract from this set.
     * @return A new set containing elements in this set but not in the other set.
     */
    operator fun times(other: SetOperations<T>): SetOperations<T> {
        val difference = SetOperations<T>()
        for (element in elements) {
            if (!other.elements.contains(element)) {
                difference.add(element)
            }
        }
        return difference
    }

    /**
     * Returns a string representation of the set.
     * @return A string containing the elements of the set.
     */
    override fun toString(): String {
        return elements.toString()
    }
}