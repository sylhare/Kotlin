package train

internal fun processJourney(line: TrainLine, listOf: List<String>): TrainLine{
    return line
}

fun trainLineOf(vararg step: TrainStep<TrainNetwork>): TrainLine = step.toMutableList()
internal typealias TrainLine = MutableList<TrainStep<TrainNetwork>>