package train.navigation

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import train.*
import kotlin.test.assertEquals

/**
 * Tests for the TrainNavigator class
 * Verifies that the navigator correctly determines train itineraries based on captor data
 * 
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
class TrainNavigatorTest {
    
    private val navigator = TrainNavigator()
    
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

    @Nested
    inner class EdgeCases {
        
        @Test
        fun `Via Waterloo to the Unknooooown!`() {
            val result = navigator.solveItinerary(euroTrip, listOf("Waterloo"))
            assertEquals(listOf(
                "trunk Warehouse".railSection,
                "trunk Transit".railSection,
                "Waterloo".switch,
                "trunk Tunnel".railSection,
                "trunk Lille 1".railSection,
                "trunk Baguette".railSection,
                "Paris".station,
            ), result)
        }

        @Test
        fun `Direct trip`() {
            val result = navigator.solveItinerary(testTrip, listOf("TrunkToTrack"))
            assertEquals(listOf(
                "trunk 0".railSection,
                "trunk 1".railSection,
                "TrunkToTrack".switch,
                "EndOfTrack".station,
            ), result)
        }

        @Test
        fun `Indirect trip`() {
            val result = navigator.solveItinerary(testTrip, listOf("TrunkToTrail"))
            assertEquals(listOf(
                "trunk 0".railSection,
                "trunk 1".railSection,
                "TrunkToTrail".switch,
                "EndOfTrail".station,
            ), result)
        }

        @Test
        fun `Loop with junction`() {
            val result = navigator.solveItinerary(testTrip, listOf("TrunkToTrail", "TrunkToTrack"))
            assertEquals(listOf(
                "trunk 0".railSection,
                "trunk 1".railSection,
                "TrunkToTrail".switch,
                "trunk 1".railSection,
                "TrunkToTrack".switch,
                "EndOfTrack".station,
            ), result)
        }
    }

    @Nested
    inner class RealTrip {
        
        @Test
        fun `Via Waterloo to Paris`() {
            val result = navigator.solveItinerary(euroTrip, listOf("Waterloo", "North Gate"))
            assertEquals(listOf(
                "trunk Warehouse".railSection,
                "trunk Transit".railSection,
                "Waterloo".switch,
                "trunk Tunnel".railSection,
                "trunk Lille 1".railSection,
                "trunk Baguette".railSection,
                "North Gate".switch,
                "Paris".station,
            ), result)
        }

        @Test
        fun `Via Waterloo to Paris with errors`() {
            val result = navigator.solveItinerary(euroTrip, listOf("Waterloo", "error", "North Gate"))
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
            ), result)
        }

        @Test
        fun `Via Waterloo to Marseille`() {
            val result = navigator.solveItinerary(euroTrip, listOf("Waterloo", "South Gate"))
            assertEquals(listOf(
                "trunk Warehouse".railSection,
                "trunk Transit".railSection,
                "Waterloo".switch,
                "trunk Tunnel".railSection,
                "trunk Lille 1".railSection,
                "trunk Baguette".railSection,
                "South Gate".switch,
                "Marseille".station,
            ), result)
        }

        @Test
        fun `Off on a wrong trail via Waterloo to Marseille`() {
            val result = navigator.solveItinerary(euroTrip, listOf("error", "Waterloo", "South Gate"))
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
            ), result)
        }

        @Test
        fun `Via King's Cross to Marseille`() {
            val result = navigator.solveItinerary(euroTrip, listOf("King's Cross", "South Gate"))
            assertEquals(listOf(
                "trunk Warehouse".railSection,
                "trunk Transit".railSection,
                "King's Cross".switch,
                "trunk Lille 1".railSection,
                "trunk Baguette".railSection,
                "South Gate".switch,
                "Marseille".station,
            ), result)
        }

        @Test
        fun `Via King's Cross to Paris`() {
            val result = navigator.solveItinerary(euroTrip, listOf("King's Cross", "North Gate"))
            assertEquals(listOf(
                "trunk Warehouse".railSection,
                "trunk Transit".railSection,
                "King's Cross".switch,
                "trunk Lille 1".railSection,
                "trunk Baguette".railSection,
                "North Gate".switch,
                "Paris".station,
            ), result)
        }

        @Test
        fun `Via King's Cross bumpy road to Paris`() {
            val result = navigator.solveItinerary(euroTrip, listOf("King's Cross", "error", "North Gate"))
            assertEquals(listOf(
                "trunk Warehouse".railSection,
                "trunk Transit".railSection,
                "King's Cross".switch,
                "trunk Lille 1".railSection,
                "trunk Baguette".railSection,
                Switch.invalid,
                "North Gate".switch,
                "Paris".station,
            ), result)
        }
    }
    
    @Nested 
    inner class ExtensionFunction {
        
        @Test
        fun `Extension function works correctly`() {
            val result = euroTrip.solveItinerary(listOf("Waterloo", "North Gate"))
            assertEquals(listOf(
                "trunk Warehouse".railSection,
                "trunk Transit".railSection,
                "Waterloo".switch,
                "trunk Tunnel".railSection,
                "trunk Lille 1".railSection,
                "trunk Baguette".railSection,
                "North Gate".switch,
                "Paris".station,
            ), result)
        }
    }
}
