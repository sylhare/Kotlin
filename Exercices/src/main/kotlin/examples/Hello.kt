package examples

import kotlin.reflect.KMutableProperty0

fun main() {
    print("Hello world!")
}

fun multiply(a: Number, b: Number): Double = when (val result = a.toDouble() * b.toDouble()) {
    -0.0 -> 0.0
    else -> result
}

// To flip boolean variable of a class like: Class::Boolean.flip()
fun KMutableProperty0<Boolean>.flip() = set(!get())