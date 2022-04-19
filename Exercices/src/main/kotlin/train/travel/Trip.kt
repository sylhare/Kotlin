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
        when {
            last && step is Station -> {
                itinerary.add(step).also { println("add $step") }
                break
            }
            step is Station -> ignores = true
            step is Switch -> when {
                step.info == currentSwitch -> {
                    itinerary.add(step).also { println("add $step") }
                    when {
                        switch.hasNext() -> currentSwitch = switch.next()
                        else -> last = true
                    }
                }
                currentSwitch == ERROR && step.info == switch.next() ->{
                    itinerary.add(Switch.invalid)
                    itinerary.add(step).also { println("add $step") }
                    when {
                        switch.hasNext() -> currentSwitch = switch.next()
                        else -> last = true
                    }
                }
                else -> ignores = true
            }
            step is Junction -> {
                switch.previous().also { println("go to $step") }
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
