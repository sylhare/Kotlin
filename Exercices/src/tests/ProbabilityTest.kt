package tests

import exercises.Probability
import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertFailsWith

internal class ProbabilityTest {
    // equals should not throw an exception
    @Test fun `same probabilities are equals`(){
        assertEquals(Probability(0.5), (Probability(0.5)))
        assertNotEquals(Probability(0.5), (Probability(0.75)))
        assertNotEquals(Probability(0.5), "hello")
        assertNotEquals(Probability(0.5), null)
    }

    // hash codes to compare and find the object in a list
    @Test fun `set operations`() {
        assertTrue(hashSetOf(Probability(0.75)).contains(Probability(0.75)))
    }

    @Test fun `can't be more than 1 or less than 0`() {
        assertFailsWith<IllegalArgumentException> {
            (Probability(2.0))
            (Probability(-2.0))
        }
    }

    @Test fun `is Greater or Smaller`() {
        assertTrue(Probability(0.3) < Probability(0.5))
        assertTrue(Probability(0.7) > Probability(0.2))
    }

    @Test fun `Not`(){
        assertEquals(Probability(0.3), Probability(0.7).not())
        assertEquals(Probability(0.3), !!Probability(0.3))
    }

    @Test fun `Not not gives the value back`(){
        assertEquals(Probability(0.3), Probability(0.3).not().not())
    }

    @Test fun `And`() {
        assertEquals(Probability(0.16),Probability(0.4).and(Probability(0.4)))
        assertEquals(Probability(0.07),Probability(0.1).and(Probability(0.7)))
        assertEquals(Probability(0.064),Probability(0.4).and(Probability(0.4)).and(Probability(0.4)))
    }

    @Test fun `Or`() {
        assertEquals(Probability(0.75), Probability(0.5).or(Probability(0.5)))
        assertEquals(Probability(0.25), Probability(0.25).or(Probability(0.0)))
        assertEquals(Probability(1.0), Probability(0.25).or(Probability(1.0)))
        assertEquals(Probability(0.92), Probability(0.8).or(Probability(0.6)))
    }

    @Test fun `Find greatest likelyhood`() {
        assertEquals(Probability(0.7), Probability.greatestOf(arrayOf(Probability(0.5), Probability(0.3), Probability(0.7))))
        assertEquals(Probability(0.7), Probability.greatestOf(arrayOf(Probability(0.5), Probability(0.7), Probability(0.7).not())))
    }

    @Test fun `Do not accept empty arrays`(){
        //assertFailsWith<java.lang.IllegalArgumentException> { Probability.greatestOf(emptyArray()) }
        assertNull(Probability.greatestOf(emptyArray()))
    }



}