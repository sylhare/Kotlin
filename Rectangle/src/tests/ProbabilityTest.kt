package tests

import exercises.Probability
import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertFailsWith

internal class ProbabilityTest {
    @Test fun `same probabilities are equals`(){
        assertEquals(Probability(0.5), (Probability(0.5)))
        assertNotEquals(Probability(0.5), (Probability(0.75)))
        assertNotEquals(Probability(0.5), "hello")
        assertNotEquals(Probability(0.5), null)
    }

    @Test fun `set operations`() {
        assertTrue(hashSetOf(Probability(0.75)).contains(Probability(0.75)))
    }

    @Test fun `can't be more than 1 or less than 0`() {
        assertFailsWith<IllegalArgumentException> {
            (Probability(2.0))
            (Probability(-2.0))
        }
    }

    @Test fun `Not, And, Or`(){

    }


}