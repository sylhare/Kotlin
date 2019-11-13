package input

import org.junit.jupiter.api.Test
import text.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class InputTest {
    @Test
    fun compareTo() {
        assertEquals(NumberInput("s"), NumberInput("s"))
        assertNotEquals(NumberInput("a"), NumberInput("s"))
    }

    @Test
    fun concatenated() {
        assertEquals("1234", listOf(1.exp, 2.exp, 3.exp, 4.exp).concatenate())
        assertEquals("1234", listOf("1", "2", "3", "4").concat())
        assertEquals("1234", listOf("1", "2", "3", "4", "+", "3").concat())
        assertEquals("1234", listOf("1", "2", "3", "4", "+", "3").concatNotInternal())
        assertEquals("1234", listOf("1", "2", "3", "4", "+", "3").concatRegex())
        assertEquals("1234", listOf("1", "2", "3", "4", "+", "3").concatOther())
    }

    @Test
    fun has() {
        assertTrue(NumberInput.has(listOf(1.exp, 2.exp, 3.exp, 4.exp), '2'))
        assertFalse(NumberInput.has(listOf(1.exp, 2.exp, 3.exp, 4.exp), 'e'))
        listOf(1,2,3,4).toExp()
    }

    @Test
    fun numberToList() {
        assertEquals(listOf(5, 4, 3, 2, 1), NumberInput.numberToList(12345))
    }
}