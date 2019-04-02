package tests.exercises

import exercises.Node
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertFailsWith

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
        B to C to D to E
        B to F
        C to D
        C to E
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

    @Test fun `Count hops`(){
        assertEquals(0, B.hopsCount(B))
        assertEquals(1, B.hopsCount(C))
        assertEquals(2, B.hopsCount(D))
        assertEquals(3, B.hopsCount(E))
        assertEquals(4, H.hopsCount(E))
        assertFailsWith<IllegalArgumentException> {  B.hopsCount(G) }
    }

}