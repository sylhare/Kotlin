package number

class Converter {

    companion object {
        val roman = mapOf(1 to "I", 5 to "V", 10 to "X", 50 to "L", 100 to "C", 500 to "D", 1000 to "M")
        val romanMap = mapOf(100 to listOf("CM", "D", "C"), 10 to listOf("XC", "L", "X"), 1 to listOf("IX", "V", "I"))

        fun simpleConvert(number: Int): String {
            if (number == 10) return "X"
            if (number == 5) return "V"
            if (number == 1) return "I"
            if (number + 1 == 10) return "IX"
            if (number + 1 == 5) return "IV"
            if (number + 1 == 1) return ""

            return if (number > 5) {
                "V" + "I".repeat(number - 5)
            } else {
                "I".repeat(number)
            }
        }

        fun complexConvert(n: Int): String {
            var solution: String

            val r1 = n / 1_000
            solution = "M".repeat(r1)

            //if (((n + 100) / 1_000) == 1) solution += "CM"
            val r2 = (n - (r1 * 1_000)) / 100
            if (r2 >= 5) solution += if (r2 == 9) "CM" else "D" + "C".repeat(r2 - 5)
            if (r2 == 4) solution += "CD"
            if (r2 < 4) solution += "C".repeat(r2)

            //if ((((n - (r1 * 1_000)) + 10) / 100) == 1) solution += "XC"
            val r3 = (n - (r1 * 1_000) - (r2 * 100)) / 10
            if (r3 >= 5) solution += if (r3 == 9) "XC" else "L" + "X".repeat(r3 - 5)
            if (r3 == 4) solution += "XL"
            if (r3 < 4) solution += "X".repeat(r3)

            //if (((n - (r1 * 1_000 + r2 * 100) + 1) / 10) == 1) solution += "IX"
            val r4 = n - (r1 * 1_000) - (r2 * 100) - (r3 * 10)
            if (r4 >= 5) solution += if (r4 == 9) "IX" else "V" + "I".repeat(r4 - 5)
            if (r4 == 4) solution += "IV"
            if (r4 < 4) solution += "I".repeat(r4)

            return solution
        }

        fun loopConvert(n: Int): String {
            var solution = ""
            var reminder = n / 1000
            var modulo = reminder * 1000

            solution += "M".repeat(reminder)

            for (x in listOf(100, 10, 1)) {
                reminder = (n - modulo) / x
                modulo += reminder * x
                if (reminder >= 5) solution += if (reminder == 9) romanMap[x]?.get(0) else romanMap[x]?.get(1) + romanMap[x]?.get(2)?.repeat(reminder - 5)
                if (reminder == 4) solution += romanMap[x]?.get(2) + romanMap[x]?.get(1)
                if (reminder < 4) solution += romanMap[x]?.get(2)?.repeat(reminder)
            }

            return solution
        }
    }
}

/*
 exercism.io : Transform arabic numbers into roman numbers
 roman numbers: http://mathworld.wolfram.com/RomanNumerals.html

 1 = I              Unit
 II III

 IV
 5 = V              5 Unit = V
 VI VII VIII

 IX
 10 = X             10 Unit = X

 ---

 10 = X             Deci
 XX XXX

 XL
 50 = L
 LX LXX LXXX

 XC
 100 = C

 ---

 100 = C            Centi
 CC CCC

 CD
 500 = D
 DC DCC DCC

 CM
 1000 = M

 ---

 1000 = M           Milli

*/