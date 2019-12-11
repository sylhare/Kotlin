package math

import exercises.Comparable
import exercises.Polygon
import exercises.Polygon.Companion.rectangle
import exercises.Polygon.Companion.square
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

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
        assertFailsWith<IllegalArgumentException> { Polygon(0, 0) }
        assertFailsWith<IllegalArgumentException> { Polygon(-2, 1) }
        assertFailsWith<IllegalArgumentException> { Polygon(1, -2) }
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