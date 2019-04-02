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

        init {
            B to A
            B to C to D to E to B to F
            C to D
            C to E
            H to B
        }
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
        assertEquals(0, B hops B)
        assertEquals(3, C hops F)
        assertEquals(1, B hops C)
        assertEquals(2, B hops D)
        assertEquals(2, B hops E)
        assertEquals(3, H hops E)
        assertFailsWith<IllegalArgumentException> {  B hops G }
    }
}