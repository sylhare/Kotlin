package train

internal fun processJourney(line: List<TrainStep<TrainNetwork>>, switchTriggered: List<String>): TrainLine {
    val result = trainLineOf()
    val visitedSteps = line.toMutableList()

    for (name in switchTriggered) {
        stepLoop@ while (visitedSteps.size > 0) {
            val current = visitedSteps.first()
            if (current.item is Switch && current.item.info != name) {
                result.add(TrainStep(current.distance, Switch(name)))
                result.add(TrainStep(current.distance + 1, RailSection.invalid))
                break@stepLoop
            } else {
                result.add(current)
                visitedSteps.removeFirst()
            }
        }
    }
    return result
}

fun trainLineOf(vararg step: TrainStep<TrainNetwork>): TrainLine = step.toMutableList()
internal typealias TrainLine = MutableList<TrainStep<TrainNetwork>>
