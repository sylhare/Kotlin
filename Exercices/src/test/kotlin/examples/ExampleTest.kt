package examples

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
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
    internal fun twoListMapTest() {
        val numbers = (1..4).toList()
        val letters = ('a'..'d').toList()
        val alphabetIndexed = letters.mapIndexed { i, v -> v to numbers[i] }.toMap()
        val alphabetZip = letters.zip(numbers).toMap()

        assertEquals(mapOf('a' to 1, 'b' to 2, 'c' to 3, 'd' to 4), alphabetIndexed)
        assertEquals(mapOf('a' to 1, 'b' to 2, 'c' to 3, 'd' to 4), alphabetZip)
    }
}