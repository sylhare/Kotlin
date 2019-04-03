package tests.exercises

import exercises.Node
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertFailsWith

internal class NodeTest {
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

    @Test internal fun `Can reach siblings`() {
        assertTrue(B canReach C)
        assertTrue(B canReach B)
    }

    @Test internal fun `Can reach not direct siblings`() {
        assertTrue(B canReach D)
        //assertTrue(H canReach E)
    }

    @Test internal fun `Cannot reach not connected nodes`(){
        assertFalse(B canReach G)
    }

    @Test internal fun `Count hops`(){
        assertEquals(0, B hops B)
        assertEquals(3, C hops F)
        assertEquals(1, B hops C)
        assertEquals(2, B hops D)
        assertEquals(2, B hops E)
        //assertEquals(3, H hops E)
        assertFailsWith<IllegalArgumentException> {  B hops G }
    }

    @Test internal fun `Cost of a path`() {
        assertTrue(10.0 == C cost F)
    }

    @Test internal fun `Return Path with the least cost`(){
        assertEquals(0.0, B.path(B).cost(), 0.001)
        assertEquals(5.0, B.path(A).cost(), 0.001)
        assertEquals(9.0, B.path(E).cost(), 0.001)
        assertEquals(10.0, C.path(F).cost(), 0.001)
        assertEquals(4, C.path(F).hopCount())
    }
}



























