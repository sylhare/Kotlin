package examples

import java.math.BigInteger

internal class Fibonacci private constructor(){

    companion object {
        val fibonacciSeq = sequence {
            var a = 0; var b = 1
            yield(1)

            while (true) {
                val count = a + b; a = b; b = count
                yield(count)
            }
        }

        /**
         * tailrec is used when the last instruction of the function is a call to itself
         * Meaning it's tail recursive.
         * Useful when n gets very big.
         */
        tailrec fun fibonacci(n: Int, a: BigInteger, b: BigInteger): BigInteger {
            return if (n == 0) b
            else fibonacci(n - 1, a + b, a)
        }

        tailrec fun factorial(n: Int, result: Int = 1): Int {
            return when (n) {
                0 ->  result
                else -> factorial(n - 1, result * n)
            }
        }
    }




}