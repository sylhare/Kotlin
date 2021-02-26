package train

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TrainNodeTest {

    companion object {
        val root = TrainNode(4, TrainStep(0, "trunk 0".railSection)).also { t1 ->
            t1.left = TrainNode(1, TrainStep(1, "toward MTL".switch)).also { t2 ->
                t2.right = TrainNode(2, TrainStep(2, "trunk 2".railSection)).also { t3 ->
                    t3.right = TrainNode(3, TrainStep(3, "MTL".station))
                }
            }
            t1.right = TrainNode(5, TrainStep(1, "toward Toronto".switch)).also { t2 ->
                t2.right = TrainNode(6, TrainStep(2, "trunk 3".railSection)).also { t3 ->
                    t3.right = TrainNode(7, TrainStep(3, "Toronto".station))
                }
            }
        }

        val nodes = listOf<TrainStep<TrainNetwork>>(
            TrainStep(0, "trunk 0".railSection),
            TrainStep(1, "toward MTL".switch),
            TrainStep(2, "trunk 2".railSection),
            TrainStep(3, "MTL".station),
            TrainStep(1, "toward Toronto".switch),
            TrainStep(2, "trunk 3".railSection),
            TrainStep(3, "Toronto".station)
        )
        val node = deserialize(nodes) ?: TrainNode(0, TrainStep(0, RailSection.invalid))
    }

    @Test
    fun addValuesTest() {
        assertEquals(listOf(4, 1, 2, 3, 5, 6, 7), treeIndexesOf(nodes))
    }

    @Test
    fun treeEqualityTest() {
        assertEquals(root, node)
        assertEquals(root.hashCode(), node.hashCode())
    }

    @Test
    fun treeNotEqualTest() {
        val expressMTL = deserialize(nodes.dropLast(3)) ?: TrainNode(0, TrainStep(0, RailSection.invalid))
        val expressToronto = deserialize(nodes.slice(setOf(0, 4, 5, 6))) ?: TrainNode(0, TrainStep(0, RailSection.invalid))
        assertNotEquals(node, expressMTL)
        assertNotEquals(node, expressToronto)
        assertNotEquals(expressToronto, expressMTL)
        assertNotEquals(node.hashCode(), expressMTL.hashCode())
        assertNotEquals(node.hashCode(), expressToronto.hashCode())
        assertNotEquals(expressToronto.hashCode(), expressMTL.hashCode())
    }

    @Test
    fun printRootTest() {
        println(root.toStringTree())
        println()
        println(node.toStringTree())
        println()
        printPreOrderTree(root)
        println()
        printPreOrderTree(node)
        println()
        printLevelOrderTree(root)
        println()
        printLevelOrderTree(node)
    }
    }
}