package hello

internal fun List<Experiments>.concatenate() = Experiments.concatenate(this)
internal fun List<String>.concatRegex() = this.takeWhile { it.contains("\\d+".toRegex()) }.joinToString("")
internal fun List<String>.concatOther() = this.takeWhile { it.toIntOrNull() != null }.joinToString("")
fun List<String>.concatNotInternal() = this.joinToString("") { it }.takeWhile { it.isDigit() }
internal fun List<String>.concat() = this.joinToString("") { it }.takeWhile { it.isDigit() }

val Number.exp get() = Experiments(this.toString())
fun  List<Number>.toExp() = this.forEach { it.exp }