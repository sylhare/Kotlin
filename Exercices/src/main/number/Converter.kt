package number

class Converter {

    companion object {
        val roman = mapOf(0 to "", 1 to "I", 5 to "V", 10 to "X")

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