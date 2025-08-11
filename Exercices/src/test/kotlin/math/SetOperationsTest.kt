package math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SetOperationsTest {
    private var setA: SetOperations<Int> = SetOperations()
    private var setB: SetOperations<Int> = SetOperations()
    private var emptySet: SetOperations<Int> = SetOperations()
    private var universalSet: SetOperations<Int> = SetOperations()

    init {
        for (i in 1..5) {
            universalSet.add(i)
            if (i <= 3) {
                setA.add(i)
            }
            if (i >= 3) {
                setB.add(i)
            }
        }
    }

    @Test
    fun testDemo() {
        assertEquals("[1, 2, 3]", setA.toString(), "Set A: $setA")
        assertEquals("[3, 4, 5]", setB.toString(), "Set B: $setB")
        assertEquals("[]", emptySet.toString(), "Empty Set: $emptySet")
        assertEquals("[1, 2, 3, 4, 5]", universalSet.toString(), "Universal Set: $universalSet")
    }

    @Test
    fun testOperations() {
        val unionSet = setA + setB
        assertEquals("[1, 2, 3, 4, 5]", unionSet.toString(), "Union of Set A and Set B: $unionSet")

        val intersectionSet = setA - setB
        assertEquals("[3]", intersectionSet.toString(), "Intersection of Set A and Set B: $intersectionSet")

        val differenceSet = setA * setB
        assertEquals("[1, 2]", differenceSet.toString(), "Difference of Set A and Set B: $differenceSet")

        assertTrue(emptySet.isSubsetOf(setA), "Is Empty Set a subset of Set A? ${emptySet.isSubsetOf(setA)}")
        assertTrue(
            setA.isSubsetOf(universalSet),
            "Is Set A a subset of Universal Set? ${setA.isSubsetOf(universalSet)}"
        )
        assertFalse(setA.isEqualTo(setB), "Are Set A and Set B equal? ${setA.isEqualTo(setB)}")
    }


    @Nested
    inner class Properties {
        private val setC = SetOperations<Int>()

        init {
            setC.add(6)
            setC.add(7)
        }

        @Test
        fun commutativity() {
            val unionAB = setA + setB
            val unionBA = setB + setA
            assertTrue(unionAB.isEqualTo(unionBA), "Is Union A + B equal to Union B + A? ${unionAB.isEqualTo(unionBA)}")
        }

        @Test
        fun associativity() {
            val unionABC = setA + (setB + setC)
            val unionACB = (setA + setB) + setC
            assertTrue(
                unionABC.isEqualTo(unionACB),
                "Is Union A + (B + C) equal to Union (A + B) + C? ${unionABC.isEqualTo(unionACB)}"
            )
        }

        @Test
        fun distributivity() {
            val distributiveSet = setA + (setB * setC)
            val distributedSet = (setA + setB) * (setA + setC)
            println("Distributive: A + (B * C): $distributiveSet")
            println("Distributed: (A + B) * (A + C): $distributedSet")
            println("Is A + (B * C) equal to (A + B) * (A + C)? ${distributiveSet.isEqualTo(distributedSet)}")
        }
    }
}