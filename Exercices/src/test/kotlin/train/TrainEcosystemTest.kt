package train

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Basic objects data test of our train ecosystem
 */
class TrainEcosystemTest {

    @Test
    fun trainNetworkEqualTest() {
        assertEquals("MTL".station, "MTL".station)
        assertEquals("toward MTL".switch, "toward MTL".switch)
        assertEquals(1.junction, 1.junction)
    }

    @Test
    fun trainNetworkNotEqualTest() {
        assertNotEquals("Toronto".station, "MTL".station)
        assertNotEquals("toward Toronto".switch, "toward MTL".switch)
        assertNotEquals(1.junction, 10.junction)
    }

    @Test
    fun railSectionPositionTest() {
        assertEquals("trunk 1".railSection, "trunk 1".railSection)
        assertEquals(1, "trunk 1".railSection.position)
        assertEquals(0, "trunk 0".railSection.position)
        assertEquals(25, "trunk 25".railSection.position)
        assertEquals(6, "trunk -6".railSection.position)
        assertEquals(null, "trunk undefined".railSection.position)
    }

    @Test
    fun trainStepTest() {
        val journey = mutableListOf<TrainStep<TrainNetwork>>()
        journey.add(TrainStep(0, "trunk 1".railSection))
        journey.add(TrainStep(0, "trunk 2".railSection))
        journey.add(TrainStep(1, "toward MTL".switch))
        journey.add(TrainStep(2, "MTL".station))
        journey.add(TrainStep(1, "toward Toronto".station))
        journey.add(TrainStep(2, "Toronto".station))
        journey.forEach {
            assertEquals(it.item, it.item)
            assertEquals(it.distance, it.distance)
            assertEquals(it, it)
        }
    }
}