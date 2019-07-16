package number

import kotlin.math.pow

class RomanConversion {

    companion object {
        private const val MAX = 3
        private val visuals = mapOf(1 to "I", 5 to "V", 10 to "X", 50 to "L", 100 to "C", 500 to "D", 1000 to "M")

        private fun base(zeros: Int) = 1 * 10.0.pow(zeros).toInt()
        private fun decompose(n: Int) = (MAX downTo 0).map { (n / base(it)) % 10 }

        private fun compose(value: Int, base: Int): String {
            if (base == 1000) return visuals.getValue(base).repeat(value)
            if (value == 9) return visuals[base] + visuals[base * 10]
            if (value >= 5) return visuals[base * 5] + visuals.getValue(base).repeat(value - 5)
            if (value == 4) return visuals[base] + visuals[base * 5]
            return visuals.getValue(base).repeat(value)
        }

        fun of(n: Int) = decompose(n).mapIndexed { i, x -> compose(x, base(MAX - i)) }.joinToString("")
    }
}
