package train

internal fun processJourney(line: List<TrainStep<TrainNetwork>>, switchTriggered: List<String>): TrainLine {
    val result = trainLineOf()
    val visitedSteps = line.toMutableList()

    for (switch in switchTriggered) {
        stepLoop@ while (visitedSteps.size > 0) {
            val current = visitedSteps.first()
            if (current.item is Switch && current.item.info != switch) {
                result.add(TrainStep(current.distance, Switch(switch)))
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


class Journey private constructor(private val flatTree: List<TrainStep<TrainNetwork>>) {
    companion object {
        private fun invalidAnswer(switch:String) = listOf(Switch(switch), RailSection.invalid)
        fun printJourney(flatTree: List<TrainStep<TrainNetwork>>, switches: List<String>): MutableList<TrainNetwork> {
            val journey = Journey(flatTree)

            for (switch in switches) {
                while (journey.index < flatTree.size) {
                    val input = flatTree[journey.index]

                    when {
                        input.item is Junction -> journey.addFromIndex(flatTree.indexOfFirst { it.item is RailSection && it.item.position == input.item.position })
                        input.item is RailSection && input.distance == journey.distance -> journey.add(input)
                        input.item is Switch && input.item.info == switch -> {
                            journey.addIndent(input, 1); break
                        }
                        input.item is Switch && input.item.info != switch -> {
                            journey.addFromIndex(flatTree.indexOfFirst { it.item is Switch && it.item.info == switch && it.distance == journey.distance + 1 }, switch, 1); break
                        }
                        else -> journey.index++
                    }
                }
            }

            journey.addStation()
            journey.printStep()
            return journey.result
        }
    }

    private val result = mutableListOf<TrainNetwork>()
    private var distance = 0
    private var index = 0

    private fun add(input: TrainStep<TrainNetwork>) {
        result.add(input.item)
        index++
    }

    private fun addIndent(input: TrainStep<TrainNetwork>, distanceBuff: Int = 0) {
        result.add(input.item)
        distance = input.distance + distanceBuff
        index++
    }

    private fun addFromIndex(jumpToIndex: Int, switch: String = "", distanceBuff: Int = 0) {
        when (jumpToIndex) {
            -1 -> result.addAll(invalidAnswer(switch))
            else -> {
                addIndent(flatTree[jumpToIndex], distanceBuff)
                index = jumpToIndex
            }
        }
    }

    private fun addStation() {
        if (flatTree[index].item is Station) {
            this.addFromIndex(index)
        }
    }

    private fun printStep() = result.forEach{ println( it ) }
}