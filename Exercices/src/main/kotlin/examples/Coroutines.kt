package examples

import kotlinx.coroutines.*
import java.time.Instant
import kotlin.system.measureTimeMillis


class Coroutines {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    companion object {
        suspend fun expensiveComputation(res: MutableList<String>) {
            delay(1000L)
            res.add("word!")
        }
    }

    suspend fun helloWorld() {
        coroutineScope.launch {
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
        runBlocking {     // but this expression blocks the main thread
            delay(3000L)  // ... while we delay for 2 seconds to keep JVM alive
            print("" + Instant.now() + " : " + "...")
        }
    }
}