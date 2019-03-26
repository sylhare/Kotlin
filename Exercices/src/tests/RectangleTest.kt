package tests

import exercises.Rectangle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertFailsWith

class RectangleTest {

    @Test
    fun hasRightPerimeter() {
        assertEquals(10, Rectangle(2, 3).perimeter())
    }

    @Test fun hasRightArea() {
        assertEquals(12, Rectangle(3, 4).area())
    }

    @Test fun hasARightAngle() {
        assertTrue(Rectangle(3, 4).hasRightAngle())
    }

    @Test fun validParameters() {
        assertFailsWith<IllegalArgumentException> {
            Rectangle(0,0)
        }
    }

    @Test fun canBeScaled() {
        assertEquals(20, Rectangle(2, 3).scale(2).perimeter())
    }


}