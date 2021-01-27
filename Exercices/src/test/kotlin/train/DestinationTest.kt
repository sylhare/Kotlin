package train

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * You manage a Train system
 * You know which lines the train can take.
 * Here are the rules to navigate on the network:
 *      - If the train takes a junction, it will go to the trunk of the specified position
 *      - If the train arrives at a station, it stops.
 *      - A train can arrive at only one station, but can go through multiple switch and junction
 *
 * Captors records when a train goes through a switch.
 * Using the captors information you need to find the itinerary of the train.
 * Here are some info on how the captors are triggered:
 *      - Captors answers are stored in chronological order (from departure to end)
 *      - Captors returns the switch `info`
 *      - Sometime a captor malfunction and you receive errored info, if that happens add the captor's info corresponding switch
 * following by an invalid trunk in the itinerary and proceed from last known location
 */
class DestinationTest {

    @Test
    fun expressToMtlTest() {
        val expressLine = trainLineOf(
            TrainStep(0, "trunk 1".railSection),
            TrainStep(0, "trunk 2".railSection),
            TrainStep(1, "toward MTL".switch),
            TrainStep(2, "MTL".station)
        )
        assertEquals(expressLine, processJourney(expressLine, listOf("toward MTL")))

    }
}