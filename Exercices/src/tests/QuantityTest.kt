package tests

import exercises.*
import exercises.Unit
import org.junit.Assert.*
import org.junit.Test

internal class QuantityTest {

    @Test internal fun `Same quantity on different metrics should be equal`() {
        assertEquals(3.teaspoons, 1.tablespoons)
        assertNotEquals(0.5.teaspoons, 1.tablespoons)
        assertEquals(1.pints, 16.ounces)
        assertEquals(1.quarts, 4.cups)
        assertEquals(0.25.gallons, 2.pints)
    }

    @Test internal fun `hashCode should be equals`() {
        assertEquals(Quantity(0.25, Unit.gallon).hashCode(), Quantity(2, Unit.pint).hashCode())
    }

    @Test internal fun `Quantity can be anti matter`() {
        assertEquals(Quantity(-3, Unit.teaspoon), Quantity(-1, Unit.tablespoon))
        assertEquals(Quantity(-3, Unit.teaspoon), - Quantity(1, Unit.tablespoon))
    }

    @Test internal fun `The Quantities can be added`() {
        assertEquals(Quantity(1, Unit.ounce),3.teaspoons + Quantity(1, Unit.tablespoon))
        assertEquals(Quantity(0.5, Unit.ounce),Quantity(6, Unit.teaspoon) + Quantity(-1, Unit.tablespoon))
    }

    @Test internal fun `The Quantities can be subsctracted`() {
        assertEquals(Quantity(0.5, Unit.ounce),Quantity(6, Unit.teaspoon) - Quantity(1, Unit.tablespoon))
        assertEquals(Quantity(1, Unit.ounce),3.teaspoons - Quantity(- 1, Unit.tablespoon))
        assertEquals(Quantity(0.5, Unit.ounce),Quantity(+6, Unit.teaspoon) - +Quantity(+1, Unit.tablespoon))
    }

    @Test internal fun `A Quantity of distance cannot be equal to one of volume`() {
        assertNotEquals(1.teaspoons, 1.inches)
        assertNotEquals(0.5.gallons, 1.feet)
    }

    @Test internal fun `Quantity of distance should match`() {
        assertEquals(22.yards, 1.chains)
        assertEquals(16.furlongs, 2.miles)
    }
}