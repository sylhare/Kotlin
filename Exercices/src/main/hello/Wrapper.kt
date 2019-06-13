package hello

internal fun List<Experiments>.concatenate() = Experiments.concatenate(this)
internal fun List<String>.concat() = this.joinToString(separator = "") { it }.takeWhile { it.isDigit() }
val Number.exp get() = Experiments(this.toString())
fun  List<Number>.toExp() = this.forEach { it.exp }