package train

class TrainNode(step: TrainStep<TrainNetwork>) {
    val distance = step.distance
    val value = step.item
    var right: TrainNode? = null
    var left: TrainNode? = null

    fun toStringTree(): String = "[$distance - $value]\nright: {${right?.toStringTree()}}\tleft: {${left?.toStringTree()}}"
    override fun toString() = "[$distance - $value]"
}

fun printLevelOrderTree(node: TrainNode) {
    val tree = mutableListOf(node)
    while (tree.isNotEmpty()) {
        val current = tree.removeFirst()
        println(current)
        if (current.left != null) tree.add(current.left!!)
        if (current.right != null) tree.add(current.right!!)
    }
}

fun printPreOrderTree(node: TrainNode?) {
    if (node!=null) {
        println(node)
        printPreOrderTree(node.left)
        printPreOrderTree(node.right)
    }
}
