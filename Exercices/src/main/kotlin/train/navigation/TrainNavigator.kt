package train.navigation

import train.*

/**
 * TrainNavigator - Solves train itinerary problems based on captor data
 * 
 * This navigator handles:
 * - Switch detection from captor data
 * - Junction navigation with position-based redirection
 * - Error detection and invalid switch handling
 * - Station arrival as termination points
 */
class TrainNavigator {
    
    /**
     * Solves the train itinerary based on the network layout and captor switch data
     * 
     * @param network The complete train network layout as a list of TrainNetwork elements
     * @param captorSwitches List of switch names detected by captors in chronological order
     * @return The actual itinerary taken by the train, including any invalid switches due to errors
     */
    fun solveItinerary(network: List<TrainNetwork>, captorSwitches: List<String>): List<TrainNetwork> {
        val validSwitches = network.filterIsInstance<Switch>().map { it.info }
        val processedSwitches = captorSwitches.map { switchName ->
            if (validSwitches.contains(switchName)) switchName else Switch.ERROR
        }
        
        return findItineraryPath(network, processedSwitches.listIterator())
    }
    
    private fun findItineraryPath(
        networkSegment: List<TrainNetwork>,
        switchIterator: ListIterator<String>
    ): List<TrainNetwork> {
        val itinerary = mutableListOf<TrainNetwork>()
        var currentSwitch = switchIterator.next()
        var ignores = false
        var last = false
        
        for (step in networkSegment) {
            when {
                last && step is Station -> {
                    itinerary.add(step)
                    break
                }
                
                step is Station -> ignores = true
                
                step is Switch -> when {
                    step.info == currentSwitch -> {
                        itinerary.add(step)
                        when {
                            switchIterator.hasNext() -> currentSwitch = switchIterator.next()
                            else -> last = true
                        }
                        ignores = false
                    }
                    
                    currentSwitch == Switch.ERROR && switchIterator.hasNext() && step.info == switchIterator.next() -> {
                        itinerary.add(Switch.invalid)
                        itinerary.add(step)
                        when {
                            switchIterator.hasNext() -> currentSwitch = switchIterator.next()
                            else -> last = true
                        }
                        ignores = false
                    }
                    
                    else -> ignores = true
                }
                
                step is Junction -> {
                    switchIterator.previous()
                    val remainingNetwork = networkSegment.dropWhile { 
                        !(it is RailSection && it.position == step.position) 
                    }
                    itinerary.addAll(findItineraryPath(remainingNetwork, switchIterator))
                    break
                }
                
                !ignores -> {
                    itinerary.add(step)
                }
            }
        }
        
        return itinerary
    }
}

/**
 * Extension function for convenient usage
 */
fun List<TrainNetwork>.solveItinerary(captorSwitches: List<String>): List<TrainNetwork> {
    return TrainNavigator().solveItinerary(this, captorSwitches)
}
