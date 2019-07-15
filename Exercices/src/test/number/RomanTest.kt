package number

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RomanTest {

    @Test
    fun simple() {
        assertEquals( "I", Converter.simpleConvert(1))
        assertEquals( "III", Converter.simpleConvert(3))
        assertEquals( "IV", Converter.simpleConvert(4))
        assertEquals( "VII", Converter.simpleConvert(7))
        assertEquals( "IX", Converter.simpleConvert(9))
        assertEquals( "X", Converter.simpleConvert(10))
    }

    @Test
    fun lessSimple() {
        assertEquals( "I", Roman.convertBasedUnit(1, RomanUnit()))
        assertEquals( "III", Roman.convertBasedUnit(3, RomanUnit()))
        assertEquals( "IV", Roman.convertBasedUnit(4, RomanUnit()))
        assertEquals( "VII", Roman.convertBasedUnit(7, RomanUnit()))
        assertEquals( "IX", Roman.convertBasedUnit(9, RomanUnit()))
        assertEquals( "X", Roman.convertBasedUnit(10, RomanUnit()))
    }
}