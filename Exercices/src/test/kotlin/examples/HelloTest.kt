import examples.A
import examples.flip
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class HelloTest {

    @Test
    internal fun booleanFlip() {
        val a = A()
        a::x.flip()
        assertTrue(a.x)
    }
}