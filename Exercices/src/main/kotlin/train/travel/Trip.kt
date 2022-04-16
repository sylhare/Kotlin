package train.travel

import train.*

fun List<TrainNetwork>.itineraryFrom(switches: List<String>): List<TrainNetwork> {
    val tripSwitches = this.filterIsInstance<Switch>().map { it.info }
    val switchIterator = switches.map { switch ->
        when {
            tripSwitches.contains(switch) -> switch
            else -> ""
        }
    }.listIterator()
    return findNext(this, switchIterator)
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

fun findNext(
    remainingTripStep: List<TrainNetwork>,
    switch: ListIterator<String>,
    isLast: Boolean = false
): List<TrainNetwork> {
    val itinerary = mutableListOf<TrainNetwork>()
    var currentSwitch = switch.next()
    println(currentSwitch)
    var ignores = false
    var last = isLast

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
            step is Switch && currentSwitch == "" && step.info == switch.next() -> {
                println("add $step")
                itinerary.add(RailSection.invalid)
                itinerary.add(step)
                when {
                    switch.hasNext() -> currentSwitch = switch.next()
                    else -> last = true
                }
            }
            step is Switch -> ignores = true
            step is Junction -> {
                println("go to $step")
                itinerary.addAll(findNext(remainingTripStep.at(step.position), switch.at(currentSwitch), false))
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
