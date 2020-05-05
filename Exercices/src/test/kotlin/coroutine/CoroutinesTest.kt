package examples

import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Instant

internal class CoroutinesTest {

    @Test
    fun `should add world async`() {
        // given
        val result = mutableListOf<String>()

        // when
        runBlocking {
            val promise = launch {
                Coroutines.expensiveComputation(result)
            }
            result.add("Hello,")
            promise.join()
        }

        // then
        assertEquals(listOf("Hello,", "word!"), result)
    }

    @Test
    fun `does not add because of delays`() {
        val result = mutableListOf<String>()
        val c = Coroutines()
        runBlocking {
            val promise = launch {
                Coroutines.expensiveComputation(result)
            }
            c.helloWorld()
            result.add("Hello,")
            promise.join()
        }
        assertEquals(listOf("word!", "Hello,"), result)
    }

    class Coroutines {

        private val coroutineScope = CoroutineScope(Dispatchers.Default)

        companion object {
            suspend fun expensiveComputation(res: MutableList<String>) {
                delay(1000L)
                res.add("word!")
            }
        }

        suspend fun helloWorld() {
            coroutineScope.launch(Dispatchers.Default) {
                delay(5000)
                println("" + Instant.now() + " : " + "All that started 5s ago")

            }
            GlobalScope.launch {
                // launch a new coroutine in background and continue
                delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                println("" + Instant.now() + " : " + "World!") // print after delay
            }
            println("" + Instant.now() + " : " + "Hello") // main thread continues while coroutine is delayed
            Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive

            println("\n" + Instant.now() + " : " + "with Coroutines")
            runBlocking {
                // but this expression blocks the main thread
                delay(3000L)  // ... while we delay for 2 seconds to keep JVM alive
                print("" + Instant.now() + " : " + "...")
            }
        }
    }
}