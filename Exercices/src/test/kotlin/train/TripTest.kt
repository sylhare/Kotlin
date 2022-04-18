package train

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import train.travel.itineraryFrom
import kotlin.test.assertEquals

/**
 * You manage a Train system
 * You know which lines the train can take.
 * Here are the rules to navigate on the network:
 *      - If the train takes a junction, it will go to the rail section of the specified position
 *      - A train can go through multiple switch and junction
 *      - If the train arrives at its station, it stops.
 *
 * Captors records when a train goes through a switch:
 *      - Captors are triggered in chronological order (from departure to end)
 *      - Captors returns the switch's "info" as a list
 *          - ie ("Waterloo", "North Gate")
 *      - Sometime a captor malfunction and you receive errored info before the actual switch
 *          - If that happens, signal it with an invalid switch in the itinerary
 *
 *  Using the captors information you need to find the itinerary of the train.
 */
internal class TripTest {
    companion object {
        val testTrip = listOf(
            "trunk 0".railSection,
            "trunk 1".railSection,
            "TrunkToTrack".switch,
            "EndOfTrack".station,
            "TrunkToTrail".switch,
            "EndOfTrail".station,
            "Junction to 1".junction
        )
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
            Switch.invalid,
            "North Gate".switch,
            "Paris".station,
        ), euroTrip.itineraryFrom(listOf("Waterloo", "error", "North Gate")))
    }

    @Test
    fun `Via Waterloo to Marseille`() {
        assertEquals(listOf(
            "trunk Warehouse".railSection,
            "trunk Transit".railSection,
            "Waterloo".switch,
            "trunk Tunnel".railSection,
            "trunk Lille 1".railSection,
            "trunk Baguette".railSection,
            "South Gate".switch,
            "Marseille".station,
        ), euroTrip.itineraryFrom(listOf("Waterloo", "South Gate")))
    }

    @Test
    fun `Off on a wrong trail via Waterloo to Marseille`() {
        assertEquals(listOf(
            "trunk Warehouse".railSection,
            "trunk Transit".railSection,
            Switch.invalid,
            "Waterloo".switch,
            "trunk Tunnel".railSection,
            "trunk Lille 1".railSection,
            "trunk Baguette".railSection,
            "South Gate".switch,
            "Marseille".station,
        ), euroTrip.itineraryFrom(listOf("error", "Waterloo", "South Gate")))
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

    @Test
    fun `Via King's Cross to Paris`() {
        assertEquals(listOf(
            "trunk Warehouse".railSection,
            "trunk Transit".railSection,
            "King's Cross".switch,
            "trunk Lille 1".railSection,
            "trunk Baguette".railSection,
            "North Gate".switch,
            "Paris".station,
        ), euroTrip.itineraryFrom(listOf("King's Cross", "North Gate")))
    }

    @Test
    fun `Via King's Cross bumpy road to Paris`() {
        assertEquals(listOf(
            "trunk Warehouse".railSection,
            "trunk Transit".railSection,
            "King's Cross".switch,
            "trunk Lille 1".railSection,
            "trunk Baguette".railSection,
            Switch.invalid,
            "North Gate".switch,
            "Paris".station,
        ), euroTrip.itineraryFrom(listOf("King's Cross", "error", "North Gate")))
    }

    @Nested
    inner class EdgeCases {
        @Test
        fun `Via Waterloo to the Unknooooown!`() {
            assertEquals(listOf(
                "trunk Warehouse".railSection,
                "trunk Transit".railSection,
                "Waterloo".switch,
                "trunk Tunnel".railSection,
                "trunk Lille 1".railSection,
                "trunk Baguette".railSection,
                "Paris".station,
            ), euroTrip.itineraryFrom(listOf("Waterloo")))
        }

        @Test
        fun `Direct trip`() {
            assertEquals(listOf(
                "trunk 0".railSection,
                "trunk 1".railSection,
                "TrunkToTrack".switch,
                "EndOfTrack".station,
            ), testTrip.itineraryFrom(listOf("TrunkToTrack")))
        }

        @Test
        fun `Indirect trip`() {
            assertEquals(listOf(
                "trunk 0".railSection,
                "trunk 1".railSection,
                "TrunkToTrail".switch,
                "EndOfTrail".station,
            ), testTrip.itineraryFrom(listOf("TrunkToTrail")))
        }

        @Test
        fun `Loop with junction`() {
            assertEquals(listOf(
                "trunk 0".railSection,
                "trunk 1".railSection,
                "TrunkToTrail".switch,
                "trunk 1".railSection,
                "TrunkToTrack".switch,
                "EndOfTrack".station,
            ), testTrip.itineraryFrom(listOf("TrunkToTrail", "TrunkToTrack")))
        }
    }
}
