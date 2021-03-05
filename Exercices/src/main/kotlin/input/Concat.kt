package input

internal fun List<String>.concatRegex() = this.takeWhile { it.contains("\\d+".toRegex()) }.joinToString("")
internal fun List<String>.concatOther() = this.takeWhile { it.toIntOrNull() != null }.joinToString("")
fun List<String>.concatNotInternal() = this.joinToString("") { it }.takeWhile { it.isDigit() }
internal fun List<String>.concat() = this.joinToString("") { it }.takeWhile { it.isDigit() }