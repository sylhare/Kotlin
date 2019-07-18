package number

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RomanUnitTest {

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
    fun romanUnitConvert() {
        assertEquals( "MMM", RomanConversion.of(3000))
        assertEquals( "MCMXCIX", RomanConversion.of(1999))
        assertEquals( "CXXVIII", RomanConversion.of(128))
        assertEquals( "XLIX", RomanConversion.of(49))
        assertEquals( "I", RomanConversion.of(1))
        assertEquals( "III", RomanConversion.of(3))
        assertEquals( "IV", RomanConversion.of(4))
        assertEquals( "VII", RomanConversion.of(7))
        assertEquals( "IX", RomanConversion.of(9))
        assertEquals( "X", RomanConversion.of(10))
    }

}