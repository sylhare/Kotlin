package hello


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FibonacciTest {

    @Test
    internal fun simpleFibonacci() {
        val res = Fibonacci.fibonacciSeq
            .take(5)
            .toList()

        assertEquals(res, listOf(1, 1, 2, 3, 5))
    }
}