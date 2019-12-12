import examples.A
import examples.flip
import examples.main
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class HelloTest {

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
        main(arrayOf(""))
    }
}