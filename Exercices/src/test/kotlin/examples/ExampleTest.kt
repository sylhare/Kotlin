package examples

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class ExampleTest {

    class A {
        var x = false
    }

    @Test
    internal fun booleanFlip() {
        val a = A()
        a::x.flip()
        assertTrue(a.x)
        a::x.flip()
        assertFalse(a.x)
    }

    @Test
    internal fun hello() {
        main()
    }

    @Test
    internal fun iterator() {
        val iterator = listOf(1, 2, 3, 4, 5).listIterator()
        assertTrue(iterator.hasNext())
        assertFalse(iterator.hasPrevious())
        assertEquals(1, iterator.next())
        assertTrue(iterator.hasPrevious())
        assertEquals(2, iterator.next())
        assertEquals(2, iterator.previous())
        assertEquals(2, iterator.next())
        assertEquals(3, iterator.next())
        assertEquals(4, iterator.next())
        assertEquals(5, iterator.next())
        assertFalse(iterator.hasNext())
        assertEquals(5, iterator.previous())
    }

    @Test
    internal fun twoListMapTest() {
        val numbers = (1..4).toList()
        val letters = ('a'..'d').toList()
        val alphabetIndexed = letters.mapIndexed { i, v -> v to numbers[i] }.toMap()
        val alphabetZip = letters.zip(numbers).toMap()

        assertEquals(mapOf('a' to 1, 'b' to 2, 'c' to 3, 'd' to 4), alphabetIndexed)
        assertEquals(mapOf('a' to 1, 'b' to 2, 'c' to 3, 'd' to 4), alphabetZip)
    }

    @Test
    internal fun wordPlayTest() {
        val result = "Hey! That's my hat hat hat!"
            .replace("[:;!?,.]".toRegex(), "").split(' ').distinct()
        assertIterableEquals(listOf("Hey", "That's", "my", "hat"), result)
    }

    @Test
    internal fun wordFrequencyTest() {
        val result: Map<String, Int> = "Hey hey! That's my hat hat hat!"
            .replace("[:;!?,.]".toRegex(), "")
            .lowercase().split(' ').groupingBy { it }.eachCount()
        assertEquals(mapOf("hey" to 2, "that's" to 1, "my" to 1, "hat" to 3), result)
    }

    @Test
    internal fun wordFrequencyCollectionTest() {
        val input = "Hey hey! That's my hat hat hat!".replace("[:;!?,.]".toRegex(), "").lowercase().split(' ')
        val result = input.associateWith { Collections.frequency(input, it) }
        assertEquals(mapOf("hey" to 2, "that's" to 1, "my" to 1, "hat" to 3), result)
    }
}
