package hello


import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class FibonacciTest {

    @Test
    internal fun simpleFibonacci() {
        val res = Fibonacci.fibonacciSeq
            .take(5)
            .toList()

        assertTrue(res.containsAll(listOf(1, 1, 2, 3, 5)))
    }
}