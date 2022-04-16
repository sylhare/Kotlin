package train.travel

import train.*
import train.Switch.Companion.ERROR

fun List<TrainNetwork>.itineraryFrom(switches: List<String>): List<TrainNetwork> {
    val tripSwitches = this.filterIsInstance<Switch>().map { it.info }
    val switchIterator = switches.map { switch ->
        when {
            tripSwitches.contains(switch) -> switch
            else -> ERROR
        }
    }.listIterator()
    return findNext(this, switchIterator)
}

private fun List<TrainNetwork>.at(position: Int): List<TrainNetwork> {
    return this.dropWhile { !(it is RailSection && it.position == position) }
}

fun findNext(
    remainingTripStep: List<TrainNetwork>,
    switch: ListIterator<String>,
): List<TrainNetwork> {
    val itinerary = mutableListOf<TrainNetwork>()
    var currentSwitch = switch.next()
    var ignores = false
    var last = false

    for (step in remainingTripStep) {
        println("$step for $currentSwitch is last $last")
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
            step is Switch && currentSwitch == ERROR && step.info == switch.next() -> {
                println("add $step")
                itinerary.add(Switch.invalid)
                itinerary.add(step)
                when {
                    switch.hasNext() -> currentSwitch = switch.next()
                    else -> last = true
                }
            }
            step is Switch -> ignores = true
            step is Junction -> {
                println("go to $step")
                switch.previous()
                itinerary.addAll(findNext(remainingTripStep.at(step.position), switch))
                break
            }
            !ignores -> {
                println("add $step")
                itinerary.add(step)
            }
        }
    }
    return itinerary
}
