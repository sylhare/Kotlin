package math

import math.Polygon.Companion.rectangle
import math.Polygon.Companion.square
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PolygonTest {

    @Test
    internal fun hasRightPerimeter() {
        assertEquals(10.0, Polygon(2, 3).perimeter())
    }

    @Test
    internal fun hasRightArea() {
        assertEquals(12.0, Polygon(3, 4).area())
    }

    @Test
    internal fun hasARightAngle() {
        assertTrue(Polygon(3, 4).hasRightAngle())
    }

    @Test
    internal fun validParameters() {
        assertThrows<IllegalArgumentException> { Polygon(0, 0) }
        assertThrows<IllegalArgumentException> { Polygon(-2, 1) }
        assertThrows<IllegalArgumentException> { Polygon(1, -2) }
    }

    @Test
    internal fun canBeScaled() {
        assertEquals(20.0, Polygon.rectangle(2, 3).scale(2).perimeter())
    }

    @Test
    internal fun `Square and rectangles`() {
        assertEquals(square(2).perimeter(), rectangle(2, 2).perimeter())
        assertEquals(square(2).area(), rectangle(2, 2).area())
        assertEquals(square(2).scale(2).perimeter(), rectangle(2, 2).scale(2).perimeter())
    }

    @Test
    internal fun `Get the biggest rectange`() {
        assertEquals(
            rectangle(4, 4).area(),
            Comparable.greatestOf(arrayOf(rectangle(4, 1), rectangle(1, 2), rectangle(1, 3), rectangle(4, 4)))?.area()
        )
        assertNull(Comparable.greatestOf(emptyArray<Polygon>()))
    }


}