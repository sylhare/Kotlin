package input

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class InputTest {
    @Test
    fun compareTo() {
        assertEquals(NumberInput("s"), NumberInput("s"))
        assertNotEquals(NumberInput("a"), NumberInput("s"))
    }

    @Test
    fun concatenatedNumberInput() {
        assertEquals("1234", listOf(1.exp, 2.exp, 3.exp, 4.exp).concatenate())
        assertEquals("", listOf(1.exp, 2.exp, NumberInput("+"), 4.exp).concatenate()) // + will be made first
    }

    @Test
    fun concatenatedString() {
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
        listOf(1, 2, 3, 4).toExp()
    }

    @Test
    fun numberToList() {
        assertEquals(listOf(5, 4, 3, 2, 1), NumberInput.intToList(12345))
        assertEquals(listOf(1), NumberInput.intToList(1))
    }
}