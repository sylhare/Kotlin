package math.number


import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class FibonacciTest {

    @Test
    internal fun noFibonacci() {
        // The take makes the fibonnaciSeq stops otherwise go for ever
        val res = Fibonacci.fibonacciSeq.take(0).toList()

        assertTrue(res.containsAll(listOf()))
    }

    @Test
    internal fun simpleFibonacci() {
        // The take makes the fibonnaciSeq stops otherwise go for ever
        val res = Fibonacci.fibonacciSeq.take(5).toList()

        assertTrue(res.containsAll(listOf(1, 1, 2, 3, 5)))
    }

    @Test
    internal fun simpleFibonacciBigger() {
        // Quickly int is not enough to keep up with like the 50 first elements
        val res = Fibonacci.fibonacciSeq.take(50).toList()
        print(res)
    }
}