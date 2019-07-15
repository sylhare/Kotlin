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

    @Test
    fun fullConvert() {
        assertEquals( "MMM", Converter.complexConvert(3000))
        assertEquals( "MCMXCIX", Converter.complexConvert(1999))
        assertEquals( "CXXVIII", Converter.complexConvert(128))
        assertEquals( "XLIX", Converter.complexConvert(49))
        assertEquals( "I", Converter.complexConvert(1))
        assertEquals( "III", Converter.complexConvert(3))
        assertEquals( "IV", Converter.complexConvert(4))
        assertEquals( "VII", Converter.complexConvert(7))
        assertEquals( "IX", Converter.complexConvert(9))
        assertEquals( "X", Converter.complexConvert(10))
    }

    @Test
    fun fullLoopConvert() {
        assertEquals( "MMM", Converter.loopConvert(3000))
        assertEquals( "MCMXCIX", Converter.loopConvert(1999))
        assertEquals( "CXXVIII", Converter.loopConvert(128))
        assertEquals( "XLIX", Converter.loopConvert(49))
        assertEquals( "I", Converter.loopConvert(1))
        assertEquals( "III", Converter.loopConvert(3))
        assertEquals( "IV", Converter.loopConvert(4))
        assertEquals( "VII", Converter.loopConvert(7))
        assertEquals( "IX", Converter.loopConvert(9))
        assertEquals( "X", Converter.loopConvert(10))
    }

}