package tests


import exercises.Unit
import exercises.Units.*
import org.junit.Assert.*
import org.junit.Test

class UnitTest {

    @Test fun `Equals`() {
        assertEquals(Unit(1, TABLESPOON), Unit(3, TEASPOON))
        assertEquals(Unit(1, OUNCE), Unit(2, TABLESPOON))
        assertEquals(Unit(1, CUP), Unit(8, OUNCE))
        assertEquals(Unit(1, PINT), Unit(2, CUP))
        assertEquals(Unit(1, QUART), Unit(2, PINT))
        assertEquals(Unit(1, GALLON), Unit(4, QUART))
        assertEquals(Unit(1, CUP), Unit(16, TABLESPOON))
        assertEquals(Unit(0.5, CUP), Unit(4, OUNCE))
    }

    @Test fun `Add`() {
        assertEquals(Unit(1, CUP), Unit(0.5, CUP) + (Unit(0.5, CUP)))
        assertEquals(Unit(0.75, PINT), Unit(1, CUP) + (Unit(4, OUNCE)))
    }
}