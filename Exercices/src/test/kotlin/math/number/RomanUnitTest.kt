package math.number

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class RomanUnitTest {

    @Test
    fun romanUnitConvert() {
        assertEquals("MMM", romanConvert(3000))
        assertEquals("MCMXCIX", romanConvert(1999))
        assertEquals("CXXVIII", romanConvert(128))
        assertEquals("XLIX", romanConvert(49))
        assertEquals("I", romanConvert(1))
        assertEquals("III", romanConvert(3))
        assertEquals("IV", romanConvert(4))
        assertEquals("VII", romanConvert(7))
        assertEquals("IX", romanConvert(9))
        assertEquals("X", romanConvert(10))
    }

    @Test
    fun failedConversionTest() {
        assertThrows<IllegalArgumentException> {  romanConvert(-1) }
    }

}