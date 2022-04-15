package train

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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
internal class JourneyTest {

    companion object {
        internal val mtlExpress = trainLineOf(
            TrainStep(0, "trunk 1".railSection),
            TrainStep(0, "trunk 2".railSection),
            TrainStep(1, "toward MTL".switch),
            TrainStep(2, "MTL".station)
        ).toList()
        internal val mtlExpressWithJunction = trainLineOf(
            TrainStep(0, "trunk 1".railSection),
            TrainStep(0, "trunk 2".railSection),
            TrainStep(1, "toward MTL".switch),
            TrainStep(1, 1.junction),
            TrainStep(2, "MTL".station)
        ).toList()
        internal val lineWithBranches = trainLineOf(
            TrainStep(0, "trunk 1".railSection),
            TrainStep(0, "trunk 2".railSection),
            TrainStep(1, "toward Ottawa".switch),
            TrainStep(2, "trunk 3".railSection),
            TrainStep(3, "toward Toronto".switch),
            TrainStep(4, "Toronto".station),
            TrainStep(1, "toward Quebec".switch),
            TrainStep(2, "trunk 4".railSection),
            TrainStep(3, "toward MTL".switch),
            TrainStep(4, "MTL".station),
        ).toList()
    }

    @Test
    fun printTest() {
        val line = lineWithBranches + TrainStep(5, Junction(5))
        line.forEach { println(it.item) }
    }

    @Test
    internal fun expressToMtlTest() {
        assertEquals(mtlExpress, processJourney(mtlExpress, listOf("toward MTL")))
    }

    @Test
    internal fun secondExpressToMtlTest() {
        val mtlExpress2 = trainLineOf(
            TrainStep(0, "trunk 1".railSection),
            TrainStep(0, "trunk 2".railSection),
            TrainStep(1, "toward MTL".switch),
            TrainStep(2, "trunk 2".railSection),
            TrainStep(3, "MTL".station)
        ).toList()
        assertEquals(mtlExpress2, processJourney(mtlExpress2, listOf("toward MTL")))
    }

    @Test
    internal fun expressToMtlWithNoiseTest() {
        assertEquals(
            trainLineOf(
                TrainStep(0, "trunk 1".railSection),
                TrainStep(0, "trunk 2".railSection),
                TrainStep(1, "toward Pancake Creek".switch),
                TrainStep(2, RailSection.invalid),
                TrainStep(1, "toward MTL".switch),
                TrainStep(2, "MTL".station)
            ), processJourney(mtlExpress, listOf("toward Pancake Creek", "toward MTL"))
        )
    }

    @Test
    internal fun journeyToMtlWithStopsTest() {
        assertNotEquals(
            trainLineOf(
                TrainStep(0, "trunk 1".railSection),
                TrainStep(0, "trunk 2".railSection),
                TrainStep(1, "toward Quebec".switch),
                TrainStep(2, "trunk 4".railSection),
                TrainStep(3, "toward MTL".switch),
                TrainStep(4, "MTL".station)
            ), processJourney(lineWithBranches, listOf("toward Quebec", "toward MTL"))
        )
    }

    @Test
    internal fun journeyToMtlWithJunctionTest() {
        assertNotEquals(
            trainLineOf(), processJourney(mtlExpressWithJunction, listOf("toward Quebec", "toward MTL"))
        )
    }
}
