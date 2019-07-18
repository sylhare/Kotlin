package hello

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

        suspend fun doSomethingUsefulOne(): Int {
            delay(1000L)
            return 1
        }

        suspend fun doSomethingUsefulTwo(): Int {
            delay(1000L)
            return 2
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


    fun runBlockingExample() = runBlocking<Unit> {
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }

    fun anotherRunBlock() = run {
        runBlocking<Unit> {
            //sampleStart
            // launch a coroutine to process some kind of incoming request
            val request = launch {
                repeat(3) { i ->
                    // launch a few children jobs
                    launch {
                        delay((i + 1) * 1000L) // variable delay 200ms, 400ms, 600ms
                        println("Coroutine $i is done")
                    }
                }
                println("request: I'm done and I don't explicitly join my children that are still active")
            }
            request.join() // wait for completion of the request, including all its children
            println("Now processing of the request is complete")
//sampleEnd
        }
    }


}