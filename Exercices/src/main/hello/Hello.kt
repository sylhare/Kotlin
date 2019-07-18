package hello

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    runBlocking {
        Coroutines().helloWorld()
        delay(1000)
    }
}