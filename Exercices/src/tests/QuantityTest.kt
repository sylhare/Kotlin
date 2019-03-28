package tests

import exercises.Unit
import exercises.Quantity
import exercises.teaspoons
import org.junit.Assert.*
import org.junit.Test

internal class QuantityTest {

    @Test internal fun `Same quantity on different metrics should be equal`() {
        assertEquals(Quantity(3, Unit.teaspoon), Quantity(1, Unit.tablespoon))
        assertNotEquals(0.5.teaspoons, Quantity(1, Unit.tablespoon))
        assertEquals(Quantity(1, Unit.pint), Quantity(16, Unit.ounce))
        assertEquals(Quantity(1, Unit.quart), Quantity(4, Unit.cup))
        assertEquals(Quantity(0.25, Unit.gallon), Quantity(2, Unit.pint))
        assertEquals(Quantity(0.25, Unit.gallon).hashCode(), Quantity(2, Unit.pint).hashCode())
    }

    @Test internal fun `Quantity can be anti matter`() {
        assertEquals(Quantity(-3, Unit.teaspoon), Quantity(-1, Unit.tablespoon))
        assertEquals(Quantity(-3, Unit.teaspoon), - Quantity(1, Unit.tablespoon))
    }

    @Test internal fun `The Quantities can be added`() {
        assertEquals(Quantity(1, Unit.ounce),Quantity(3, Unit.teaspoon) + Quantity(1, Unit.tablespoon))
        assertEquals(Quantity(0.5, Unit.ounce),Quantity(6, Unit.teaspoon) + Quantity(-1, Unit.tablespoon))
    }

    @Test internal fun `The Quantities can be subsctracted`() {
        assertEquals(Quantity(0.5, Unit.ounce),Quantity(6, Unit.teaspoon) - Quantity(1, Unit.tablespoon))
        assertEquals(Quantity(1, Unit.ounce),Quantity(3, Unit.teaspoon) - Quantity(- 1, Unit.tablespoon))
        assertEquals(Quantity(0.5, Unit.ounce),Quantity(+6, Unit.teaspoon) - +Quantity(+1, Unit.tablespoon))
    }

    @Test internal fun `A Quantity of inch cannot be a teaspoon`() {
        assertNotEquals(Quantity(1, Unit.teaspoon), Quantity(1, Unit.inch))
    }
}