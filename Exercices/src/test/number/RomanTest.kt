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

}