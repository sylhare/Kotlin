package examples

import kotlinx.coroutines.*
import kotlin.reflect.KMutableProperty0


class Hello

fun main() {
    runBlocking {
        Coroutines().helloWorld()
    }
}

class A {
    var x = false
}

fun KMutableProperty0<Boolean>.flip() = set(!get())