package hello

class Fibonacci {

    companion object {
        val fibonacciSeq = sequence {
            var a = 0
            var b = 1

            yield(1)

            while (true) {
                yield(a + b)

                val tmp = a + b
                a = b
                b = tmp
            }
        }
    }

}