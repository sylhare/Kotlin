package examples

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ExampleTest {

    class A {
        var x = false
    }

    @Test
    internal fun booleanFlip() {
        val a = A()
        a::x.flip()
        assertTrue(a.x)
        a::x.flip()
        assertFalse(a.x)
    }

    @Test
    internal fun hello() {
        main()
    }
}