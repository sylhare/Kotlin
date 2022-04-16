package train.travel

import train.*

fun List<TrainNetwork>.itineraryFrom(switches: List<String>): List<TrainNetwork> {
    return findNext(this, false, switches.listIterator())
}

private fun List<TrainNetwork>.at(position: Int): List<TrainNetwork> {
    return this.dropWhile { !(it is RailSection && it.position == position) }
}

private fun ListIterator<String>.at(currentSwitch: String): ListIterator<String> {
    while (this.hasNext() && this.next() != currentSwitch) {
        this.next()
    }
    this.previous()
    return this
}

fun findNext(remainingTripStep: List<TrainNetwork>, dd: Boolean, switch: ListIterator<String>): List<TrainNetwork> {
    val itinerary = mutableListOf<TrainNetwork>()
    var currentSwitch = switch.next()
    println(currentSwitch)
    var ignores = false
    var last = false

    for (step in remainingTripStep) {
        println(step)
        when {
            last && step is Station -> {
                println("add $step")
                itinerary.add(step)
                break
            }
            step is Station -> ignores = true
            step is Switch && step.info == currentSwitch -> {
                println("add $step")
                itinerary.add(step)
                when {
                    switch.hasNext() -> currentSwitch = switch.next()
                    else -> last = true
                }
            }
            step is Switch -> {
                ignores = true
            }
            step is Junction -> {
                println("go to $step")
                itinerary.addAll(findNext(remainingTripStep.at(step.position), false, switch.at(currentSwitch)))
                break
            }
            !ignores /* and step is a rail section that is not invalid */ -> {
                println("add $step")
                itinerary.add(step)
            }
        }
    }
    return itinerary
}
