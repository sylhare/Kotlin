package tests.exercises

import exercises.Comparable
import exercises.Polygon
import exercises.Polygon.Companion.rectangle
import exercises.Polygon.Companion.square
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class PolygonTest {

    @Test
    fun hasRightPerimeter() {
        assertEquals(10, Polygon(2, 3).perimeter())
    }

    @Test fun hasRightArea() {
        assertEquals(12, Polygon(3, 4).area())
    }

    @Test fun hasARightAngle() {
        assertTrue(Polygon(3, 4).hasRightAngle())
    }

    @Test fun validParameters() {
        assertFailsWith<IllegalArgumentException> {
            Polygon(0,0)
        }
    }

    @Test fun canBeScaled() {
        assertEquals(20, Polygon.rectangle(2, 3).scale(2).perimeter())
    }

    @Test fun `Square and rectangles`() {
        assertEquals(square(2).perimeter(), rectangle(2, 2).perimeter())
        assertEquals(square(2).area(), rectangle(2, 2).area())
        assertEquals(square(2).scale(2).perimeter(), rectangle(2, 2).scale(2).perimeter())
    }

    @Test fun `Get the biggest rectange`() {
        assertEquals(rectangle(4,4).area(),
                Comparable.greatestOf(arrayOf(rectangle(4,1), rectangle(1,2 ), rectangle(1, 3), rectangle(4, 4)))?.area())
        assertNull(Comparable.greatestOf(emptyArray<Polygon>()))
    }


}