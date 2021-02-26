package train

class TrainNode(val index: Int, step: TrainStep<TrainNetwork>) {
    val distance = step.distance
    val value = step.item
    var right: TrainNode? = null
    var left: TrainNode? = null

    fun toStringTree(): String =
        "$this\nleft: {${left?.toStringTree()}}\tright: {${right?.toStringTree()}}"

    override fun toString() = "[$distance - $value]"
    override fun equals(other: Any?): Boolean = this === other ||
            other is TrainNode && other.distance == this.distance && other.value == this.value
            && other.left == left && other.right == right

    override fun hashCode(): Int = this.value.hashCode() + this.left?.value.hashCode() + this.right?.value.hashCode()
}

fun treeIndexesOf(line: List<TrainStep<TrainNetwork>>, index: Int = 0): List<Int> {
    val branchTwoRoot = line.map { it.distance }.run { this.lastIndexOf(this[1]) }
    return if (branchTwoRoot != 1) {
        val branchOne = treeIndexesOf(line.subList(1, branchTwoRoot), index + 1).toTypedArray()
        val rootStart = index + branchOne.size + 1
        val branchTwo = treeIndexesOf(line.subList(branchTwoRoot, line.size), rootStart + 1).toTypedArray()
        val listIndexed = listOf(rootStart, *branchOne, *branchTwo)
        listIndexed
    } else {
        (index until index + line.size).toList()
    }
}

fun deserialize(line: List<TrainStep<TrainNetwork>>): TrainNode? {
    var root: TrainNode? = null
    val indexes = treeIndexesOf(line)
    line.withIndex().forEach { (i, step) -> root = insert(root, step, indexes[i]) }
    return root
}

fun insert(root: TrainNode?, data: TrainStep<TrainNetwork>, index: Int): TrainNode =
    if (root == null) TrainNode(index, data) else when {
        index <= root.index -> root.also { it.left = insert(it.left, data, index) }
        else -> root.also { it.right = insert(it.right, data, index) }
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
    if (node != null) {
        println(node)
        printPreOrderTree(node.left)
        printPreOrderTree(node.right)
    }
}

fun getNodesAtDistance(root: TrainNode, distance: Int) {

}
