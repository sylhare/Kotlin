package train

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Basic objects data test of our train ecosystem
 */
internal class TrainEcosystemTest {

    @Test
    internal fun trainNetworkEqualTest() {
        assertEquals("MTL".station, "MTL".station)
        assertEquals("toward MTL".switch, "toward MTL".switch)
        assertEquals(1.junction, 1.junction)
    }

    @Test
    internal fun trainNetworkNotEqualTest() {
        assertNotEquals("Toronto".station, "MTL".station)
        assertNotEquals("toward Toronto".switch, "toward MTL".switch)
        assertNotEquals(1.junction, 10.junction)
    }

    @Test
    internal fun railSectionPositionTest() {
        assertEquals("trunk 1".railSection, "trunk 1".railSection)
        assertEquals(1, "trunk 1".railSection.position)
        assertEquals(0, "trunk 0".railSection.position)
        assertEquals(25, "trunk 25".railSection.position)
        assertEquals(6, "trunk -6".railSection.position)
        assertEquals(null, "trunk undefined".railSection.position)
    }

    @Test
    internal fun trainStepTest() {
        val journey = trainLineOf(
            TrainStep(0, "trunk 1".railSection),
            TrainStep(0, "trunk 2".railSection),
            TrainStep(1, "toward MTL".switch),
            TrainStep(2, "MTL".station),
            TrainStep(1, "toward Toronto".station),
            TrainStep(2, "Toronto".station)
        )
        journey.forEach {
            assertEquals(it.item, it.item)
            assertEquals(it.distance, it.distance)
            assertEquals(it, it)
        }
    }
}