package train

import org.junit.jupiter.api.Test
import train.TrainNodeTest.Companion.lineWithBranches
import train.TrainNodeTest.Companion.mtlExpress
import train.TrainNodeTest.Companion.mtlExpressWithJunction
import train.travel.processJourney
import train.travel.trainLineOf
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class JourneyTest {

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
