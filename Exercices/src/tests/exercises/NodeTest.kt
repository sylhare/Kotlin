package tests.exercises

import exercises.Node
import org.junit.Assert.*
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
            B cost 5 to A
            B cost 6 to C cost 1 to D cost 2 to E cost 3 to B cost 4 to F
            C cost 7 to D
            C cost 8 to E
//            H cost 1.0 to B


        }
    }

    @Test fun `Can reach siblings`() {
        assertTrue(B canReach C)
        assertTrue(B canReach B)
    }

    @Test fun `Can reach not direct siblings`() {
        assertTrue(B canReach D)
        //assertTrue(H canReach E)
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
        //assertEquals(3, H hops E)
        assertFailsWith<IllegalArgumentException> {  B hops G }
    }

    @Test fun `Cost of a path`() {
        assertTrue(10.0 == C cost F)
    }
}