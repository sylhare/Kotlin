package input

import input.NumberInput.Companion.has
import input.NumberInput.Companion.input
import input.NumberInput.Companion.sortConcatenate
import input.NumberInput.Companion.toNumberInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class InputTest {
    @Test
    fun equalityTest() {
        assertEquals(NumberInput("s"), NumberInput("s"))
        assertEquals(NumberInput("123"), NumberInput("123"))
        assertNotEquals(NumberInput("a"), NumberInput("s"))
    }

    @Test
    fun concatenatedNumberInput() {
        assertEquals("1234", listOf(4, 3, 2, 1).toNumberInput().sortConcatenate())
        assertEquals("1234", listOf(4, 3, 2, 1).toNumberInput().sortConcatenate())
        assertEquals("1", listOf(1.input).sortConcatenate())
        assertEquals("", listOf(1.input, 2.input, NumberInput("+"), 4.input).sortConcatenate()) // + will be made first
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
        assertTrue(listOf(1, 2, 3, 4).toNumberInput().has('2'))
        assertFalse(listOf(1, 2, 3, 4).toNumberInput().has('5'))
        assertFalse(listOf(1, 2, 3, 4).toNumberInput().has('e'))

    }

    @Test
    fun numberToList() {
        val range = 1..5
        assertEquals(listOf(1, 2, 3, 4, 5), NumberInput.intToList(12345))
        assertEquals(range.toList(), NumberInput.intToList(12345))
        assertEquals(listOf(1), NumberInput.intToList(1))
    }
}