package math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class ProbabilityTest {
    // equals should not throw an exception
    @Test internal fun `same probabilities are equals`(){
        val p = Probability(0.3)
        assertEquals(p, p)
        assertEquals(Probability(0.5), (Probability(0.5)))
        assertNotEquals(Probability(0.5), (Probability(0.75)))
        assertNotEquals(Probability(0.5), 0.5)
        assertNotEquals(Probability(0.5), "hello")
        assertNotEquals(Probability(0.5), null)
    }

    // hash codes to compare and find the object in a list
    @Test internal fun `set operations`() {
        assertTrue(hashSetOf(Probability(0.75)).contains(Probability(0.75)))
    }

    @Test internal fun `can't be more than 1 or less than 0`() {
        assertThrows<IllegalArgumentException> { (Probability(-2.0)) }
        assertThrows<IllegalArgumentException> { (Probability(2.0)) }
    }

    @Test internal fun `is Greater or Smaller`() {
        assertTrue(Probability(0.3) < Probability(0.5))
        assertTrue(Probability(0.7) > Probability(0.2))
        assertTrue(Probability(0.7).isGreaterThan(Probability(0.2)))
        assertFalse(Probability(0.4) isGreaterThan Probability(0.9))
    }

    @Test internal fun `Not`(){
        assertEquals(Probability(0.3), Probability(0.7).not())
        assertEquals(Probability(0.3), !!Probability(0.3))
    }

    @Test internal fun `Not not gives the value back`(){
        assertEquals(Probability(0.3), Probability(0.3).not().not())
    }

    @Test internal fun `And`() {
        assertEquals(Probability(0.16), Probability(0.4).and(Probability(0.4)))
        assertEquals(Probability(0.07), Probability(0.1).and(Probability(0.7)))
        assertEquals(Probability(0.064), Probability(0.4).and(Probability(0.4)).and(Probability(0.4)))
    }

    @Test internal fun `Or`() {
        assertEquals(Probability(0.75), Probability(0.5).or(Probability(0.5)))
        assertEquals(Probability(0.25), Probability(0.25).or(Probability(0.0)))
        assertEquals(Probability(1.0), Probability(0.25).or(Probability(1.0)))
        assertEquals(Probability(0.92), Probability(0.8).or(Probability(0.6)))
    }

    @Test internal fun `Find greatest likelyhood`() {
        assertEquals(Probability(0.7), arrayOf(Probability(0.5), Probability(0.3), Probability(0.7)).best())
        assertEquals(Probability(0.7), arrayOf(Probability(0.5), Probability(0.7), Probability(0.7).not()).best())
        assertEquals(
            Probability(0.7),
            Comparable.greatestOf(arrayOf(Probability(0.5), Probability(0.3), Probability(0.7)))
        )
    }

    @Test internal fun `Do not accept empty arrays`(){
        assertNull(Comparable.greatestOf(emptyArray<Probability>()))
        assertEquals(null, emptyArray<Probability>().best())
    }



}