package tests.exercises

import exercises.Node
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NodeTest {
    companion object {
        val A = Node()
        val B = Node()
        val C = Node()
        val D = Node()
        val E = Node()
        val F = Node()
        val G = Node()
        val H = Node()
    }

    @Before()
    fun init() {
        B to A
        B to C
        B to F
        C to D
        C to D
        C to E
        D to E
        E to B
        H to B
    }

    @Test fun `Can reach siblings`() {
        assertTrue(B canReach C)
        assertTrue(B canReach B)
    }

    @Test fun `Can reach not direct siblings`() {
        assertTrue(B canReach D)
        assertTrue(H canReach E)
    }

    @Test fun `Cannot reach not connected nodes`(){
        assertFalse(B canReach G)
    }

}