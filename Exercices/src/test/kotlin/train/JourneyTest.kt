package train

import org.junit.jupiter.api.Test
import train.travel.processJourney
import train.travel.trainLineOf
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class JourneyTest {

    companion object {
        internal val mtlExpress = train.trainLineOf(
            TrainStep(0, "trunk 1".railSection),
            TrainStep(0, "trunk 2".railSection),
            TrainStep(1, "toward MTL".switch),
            TrainStep(2, "MTL".station)
        ).toList()
        internal val mtlExpressWithJunction = train.trainLineOf(
            TrainStep(0, "trunk 1".railSection),
            TrainStep(0, "trunk 2".railSection),
            TrainStep(1, "toward MTL".switch),
            TrainStep(1, 1.junction),
            TrainStep(2, "MTL".station)
        ).toList()
        internal val lineWithBranches = train.trainLineOf(
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
    fun deserializeLines() {
        println(TrainTree.deserialize(mtlExpress).toStringTree())
        println(TrainTree.deserialize(mtlExpressWithJunction).toStringTree())
        println(TrainTree.deserialize(lineWithBranches).toStringTree())
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
