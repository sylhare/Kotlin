package hello

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class ExperimentsTest {
    @Test
    fun compareTo() {
        assertEquals(Experiments("s"), Experiments("s"))
        assertNotEquals(Experiments("a"), Experiments("s"))
    }

    @Test
    fun concatenated() {
        assertEquals("1234", listOf(1.exp, 2.exp, 3.exp, 4.exp).concatenate())
        assertEquals("1234", listOf("1", "2", "3", "4").concat())
        assertEquals("1234", listOf("1", "2", "3", "4", "+", "3").concat())
    }

    @Test
    fun has() {
        assertTrue(Experiments.has(listOf(1.exp, 2.exp, 3.exp, 4.exp), '2'))
        assertFalse(Experiments.has(listOf(1.exp, 2.exp, 3.exp, 4.exp), 'e'))
        listOf(1,2,3,4).toExp()
    }
}