package quantity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QuantityTest {

    @Test internal fun `do toString need to be tested?`() {
        assertTrue(3.teaspoons.toString().contains("Quantity(unit="))
        assertTrue(3.teaspoons.toString().contains("amount="))
    }

    @Test internal fun `Same quantity on different metrics should be equal`() {
        val quart = 1.quarts
        assertEquals(quart, quart)
        assertEquals(3.teaspoons, 1.tablespoons)
        assertNotEquals(0.5.teaspoons, 1.tablespoons)
        assertEquals(1.pints, 16.ounces)
        assertEquals(1.quarts, 4.cups)
        assertEquals(0.25.gallons, 2.pints)
    }

    @Test internal fun `hashCode should be equals`() {
        assertEquals(RatioQuantity(0.25, Unit.gallon).hashCode(), RatioQuantity(2, Unit.pint).hashCode())
        //assertNotEquals(RatioQuantity(1, Unit.teaspoon).hashCode(), RatioQuantity(1, Unit.inch).hashCode())  // Not necessary
        assertEquals(RatioQuantity(-40, Unit.fahrenheit).hashCode(), RatioQuantity(-40, Unit.celsius).hashCode())
    }

    @Test internal fun `Quantity can be anti matter`() {
        assertEquals(RatioQuantity(-3, Unit.teaspoon), RatioQuantity(-1, Unit.tablespoon))
        assertEquals(RatioQuantity(-3, Unit.teaspoon), -RatioQuantity(1, Unit.tablespoon))
    }

    @Test internal fun `The Quantities can be added`() {
        assertEquals(RatioQuantity(1, Unit.ounce),3.teaspoons + RatioQuantity(1, Unit.tablespoon))
        assertEquals(RatioQuantity(0.5, Unit.ounce), RatioQuantity(6, Unit.teaspoon) + RatioQuantity(-1, Unit.tablespoon))
    }

    @Test internal fun `The Quantities can be subsctracted`() {
        assertEquals(RatioQuantity(0.5, Unit.ounce), RatioQuantity(6, Unit.teaspoon) - RatioQuantity(1, Unit.tablespoon))
        assertEquals(RatioQuantity(1, Unit.ounce),3.teaspoons - RatioQuantity(-1, Unit.tablespoon))
        assertEquals(RatioQuantity(0.5, Unit.ounce), RatioQuantity(+6, Unit.teaspoon) - +RatioQuantity(+1, Unit.tablespoon))
    }

    @Test internal fun `A Quantity of distance cannot be equal to one of volume`() {
        assertNotEquals(1.teaspoons, 1.inches)
        assertNotEquals(0.5.gallons, 1.feet)
    }

    @Test internal fun `Quantity of distance should match`() {
        assertEquals(22.yards, 1.chains)
        assertEquals(16.furlongs, 2.miles)
    }

    @Test internal fun `Celsius to Fahrenheit`() {
        val celsius = 1.celsius
        assertEquals(celsius, celsius)
        assertEquals(0.celsius, 32.fahrenheits)
        assertNotEquals(32.celsius, 32.fahrenheits)
        assertEquals(10.celsius, 50.fahrenheits)
        assertEquals(100.celsius, 212.fahrenheits)
        assertEquals((-40).celsius, (-40).fahrenheits)
    }

    @Test internal fun `Fahrenheit to Celsius`() {
        assertEquals(32.fahrenheits, 0.celsius)
        assertEquals(50.fahrenheits, 10.celsius)
        assertEquals(212.fahrenheits, 100.celsius)
        assertEquals((-40).fahrenheits, (-40).celsius)
    }

    @Test internal fun `Kelvin can be Celsius and Fahrenheit`(){
        assertEquals(0.kelvins, (-459.67).fahrenheits)
        assertEquals((-441.67).fahrenheits, 10.kelvins)
        assertEquals(27.kelvins, (-246.15).celsius)
        assertEquals(105.85.celsius, 379.kelvins)
        assertNotEquals(0.celsius, 0.kelvins)
    }

    @Test internal fun `Gas Mark can be Celsius and Fahrenheit`() {
        assertEquals(3.gasmarks, 325.fahrenheits)
        assertNotEquals(10.gasmarks, 0.fahrenheits)
        assertEquals(325.fahrenheits, 3.gasmarks)
        assertEquals(8.gasmarks, 232.222222222222222.celsius)   // Gasmarks sucks with celsius, it is a fahrenheit made unit
    }

    @Test internal fun `rankine to else`() {
        assertEquals(0.rankines, 0.kelvins)
        assertEquals(527.67.rankines, 20.celsius)
        assertEquals((-458.67).fahrenheits, 1.rankines)
    }


    @Test internal fun `Ratio and Interval quantities can't mix`() {
        assertNotEquals(0.teaspoons, 0.kelvins)
        assertNotEquals(2.yards, 2.celsius)
        assertNotEquals(2.yards, 2)
        assertNotEquals(2.celsius, 2)
        assertNotEquals((-50).fahrenheits, 50.furlongs)
    }

// Should not compile
//    @Test internal fun `Substraction and Addition do not work on temperature`(){
//             - (-50).fahrenheits
//             20.celsius - 50.kelvins
//             10.celsius + 10.fahrenheits
//    }

}