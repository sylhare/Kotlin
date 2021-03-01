package train

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 *  Rule for ordered binary tree
 *  Each tree node has an index that is ascending the further you go in the leaves.
 *  Right branches have higher index than left branches
 *  If a left branch has leaves the branching node index must be higher than the last rightest leaf index.
 *
 *  Here is an example tree:            Depth level
 *              7                           0
 *            /  \
 *           1    8                         1
 *          /      \
 *         4        9                       2
 *       /  \
 *      2    5                              3
 *     /      \
 *    3        6                            4
 *
 * The serialized tree reads:   7, 1, 4, 2, 3, 5, 6, 8, 9
 * The serialized depth reads:  0, 1, 2, 3, 4, 3, 4, 1, 2
 *
 */
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

        private val italyNodes = listOf<TrainStep<TrainNetwork>>(
            TrainStep(0, "trunk 0".railSection),
            TrainStep(1, "toward Rome".switch),
            TrainStep(2, "trunk 2".railSection),
            TrainStep(3, "toward Milan".station),
            TrainStep(4, "Milan".station),
            TrainStep(3, "toward Vatican".station),
            TrainStep(4, "Vatican".station),
            TrainStep(1, "toward Venice".switch),
            TrainStep(2, "Venice".station)
        )
        val italyLine = deserialize(italyNodes) ?: TrainNode(0, TrainStep(0, RailSection.invalid))
    }

    @Test
    fun treeEqualityTest() {
        assertEquals(root, node)
        assertEquals(root.hashCode(), node.hashCode())
    }

    @Test
    fun treeNotEqualTest() {
        val expressMTL = deserialize(nodes.dropLast(3)) ?: TrainNode(0, TrainStep(0, RailSection.invalid))
        val expressToronto =
            deserialize(nodes.slice(setOf(0, 4, 5, 6))) ?: TrainNode(0, TrainStep(0, RailSection.invalid))
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
        println(italyLine.toStringTree())
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

    @Test
    fun addValuesTest() {
        assertEquals(listOf(4, 1, 2, 3, 5, 6, 7), treeIndexesOf(nodes))
        assertEquals(listOf(7, 1, 4, 2, 3, 5, 6, 8, 9), treeIndexesOf(italyNodes))
    }

    @Test
    fun getNodesAtDistanceSimpleTreeTest() {
        assertEquals(2, countNodesAtDistance(root, 2))
        assertEquals(2, countNodesAtDistance(root, 1))
        assertEquals(1, countNodesAtDistance(root, 0))
        assertEquals(0, countNodesAtDistance(null, 0))
    }
}