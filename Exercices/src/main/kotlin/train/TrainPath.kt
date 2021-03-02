package train

object TrainPath {
    fun from(root: TrainNode, switch: List<String>): List<TrainNetwork> = when(root.childCount()) {
        1 -> listOf(root.value) + from(root.right!!, switch)
        2 -> selectSwitchPath(root, switch)
        else -> listOf(root.value)
    }

    private fun selectSwitchPath(root: TrainNode, switch: List<String>): MutableList<TrainNetwork> {
        var currentSwitch = switch
        val result = mutableListOf(root.value)
        do {
            val leftPath = switchPath(root.left!!, currentSwitch)
            val rightPath = switchPath(root.right!!, currentSwitch)
            if (leftPath.isEmpty() && rightPath.isEmpty()) {
                result += listOf(Switch(switch[0]), RailSection.invalid)
                currentSwitch = switch.drop(1)
            } else result += leftPath + rightPath
        } while (leftPath.isEmpty() && rightPath.isEmpty())
        return result
    }

    private fun switchPath(root: TrainNode, switch: List<String>) = when {
        root.value is Switch && root.value.info == switch[0] -> listOf(root.value) + from(root.right!!, switch.drop(1))
        else -> listOf()
    }

    fun printLevelOrderTree(node: TrainNode) {
        val tree = mutableListOf(node)
        while (tree.isNotEmpty()) {
            val current = tree.removeFirst()
            println(current)
            current.left?.run { tree.add(this) }
            current.right?.run { tree.add(this) }
        }
    }

    fun printPreOrderTree(node: TrainNode?) {
        if (node != null) {
            println(node)
            printPreOrderTree(node.left)
            printPreOrderTree(node.right)
        }
    }
}