package examples

import kotlin.reflect.KMutableProperty0

fun main() {
    print("Hello world!")
}

// To flip boolean variable of a class like: Class::Boolean.flip()
fun KMutableProperty0<Boolean>.flip() = set(!get())