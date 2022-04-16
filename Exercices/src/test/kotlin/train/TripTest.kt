package train

import org.junit.jupiter.api.Test
import train.travel.itineraryFrom
import kotlin.test.assertEquals

internal class TripTest {
    companion object {
        val euroTrip = listOf(
            "trunk Warehouse".railSection,
            "trunk Transit".railSection,
            "Waterloo".switch,
            "trunk Tunnel".railSection,
            "trunk Lille 1".railSection,
            "trunk Baguette".railSection,
            "North Gate".switch,
            "Paris".station,
            "South Gate".switch,
            "Marseille".station,
            "King's Cross".switch,
            "Lille 1 Junction".junction
        )
    }

    @Test
    fun `Via Waterloo to Paris`() {
        assertEquals(listOf(
            "trunk Warehouse".railSection,
            "trunk Transit".railSection,
            "Waterloo".switch,
            "trunk Tunnel".railSection,
            "trunk Lille 1".railSection,
            "trunk Baguette".railSection,
            "North Gate".switch,
            "Paris".station,
        ), euroTrip.itineraryFrom(listOf("Waterloo", "North Gate")))
    }

    @Test
    fun `Via Waterloo to Paris with errors`() {
        assertEquals(listOf(
            "trunk Warehouse".railSection,
            "trunk Transit".railSection,
            "Waterloo".switch,
            "trunk Tunnel".railSection,
            "trunk Lille 1".railSection,
            "trunk Baguette".railSection,
            RailSection.invalid,
            "North Gate".switch,
            "Paris".station,
        ), euroTrip.itineraryFrom(listOf("Waterloo", "error", "North Gate")))
    }

    @Test
    fun `Via King's Cross to Marseille`() {
        assertEquals(listOf(
            "trunk Warehouse".railSection,
            "trunk Transit".railSection,
            "King's Cross".switch,
            "trunk Lille 1".railSection,
            "trunk Baguette".railSection,
            "South Gate".switch,
            "Marseille".station,
        ), euroTrip.itineraryFrom(listOf("King's Cross", "South Gate")))
    }
}
