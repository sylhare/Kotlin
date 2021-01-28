package examples


import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

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

    @Test
    internal fun tailrecFibonacciTest() {
        // Get the n number in the fibonacci sequence
        val res = Fibonacci.fibonacci(4, BigInteger.ONE, BigInteger.ONE)
        assertEquals(BigInteger.valueOf(5), res)
        assertEquals(BigInteger.ONE, Fibonacci.fibonacci(0, BigInteger.ONE, BigInteger.ONE))
    }

    @Test
    internal fun factorialTest() {
        assertEquals(24, Fibonacci.factorial(4))
        assertEquals(6, Fibonacci.factorial(3))
        assertEquals(1, Fibonacci.factorial(0))
    }
}