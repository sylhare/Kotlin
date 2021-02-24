package train

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class TrainNodeTest {

    companion object {
        val root = TrainNode(TrainStep(0, "trunk 0".railSection)).also { t1 ->
            t1.left = TrainNode(TrainStep(1, "toward MTL".switch)).also { t2 ->
                t2.left = TrainNode(TrainStep(2, "trunk 2".railSection)).also { t3 ->
                    t3.left = TrainNode(TrainStep(3, "MTL".station))
                }
            }
            t1.right = TrainNode(TrainStep(1, "toward Toronto".switch)).also { t2 ->
                t2.left = TrainNode(TrainStep(2, "trunk 3".railSection)).also { t3 ->
                    t3.left = TrainNode(TrainStep(3, "Toronto".station))
                }
            }
        }
    }

    @Test
    fun printRootTest() {
        printPreOrderTree(root)
        println()
        printLevelOrderTree(root)
        println()
    }
}