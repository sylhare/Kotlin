package hello


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HelloWorldTest {

    @Test
    fun greetingTest() {
        assertEquals("Hello, world!", HelloWorld().greeting())
    }
}