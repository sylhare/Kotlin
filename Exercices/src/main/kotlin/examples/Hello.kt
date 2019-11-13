package examples

import kotlinx.coroutines.*
import kotlin.reflect.KMutableProperty0

fun main(args: Array<String>) {
    runBlocking {
        Coroutines().helloWorld()
        delay(1000)
    }
}

class A {
    var x = false
}

fun KMutableProperty0<Boolean>.flip() = set(!get())