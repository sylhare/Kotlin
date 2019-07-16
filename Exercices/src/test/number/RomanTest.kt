package number

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RomanUnitTest {

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
        assertEquals( "I", RomanUnit.convertBasedUnit(1, RomanUni()))
        assertEquals( "III", RomanUnit.convertBasedUnit(3, RomanUni()))
        assertEquals( "IV", RomanUnit.convertBasedUnit(4, RomanUni()))
        assertEquals( "VII", RomanUnit.convertBasedUnit(7, RomanUni()))
        assertEquals( "IX", RomanUnit.convertBasedUnit(9, RomanUni()))
        assertEquals( "X", RomanUnit.convertBasedUnit(10, RomanUni()))
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
    fun loopConvert() {
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