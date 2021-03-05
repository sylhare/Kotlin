package train

class TrainNode(val index: Int, step: TrainStep<TrainNetwork>) {
    private val distance = step.distance
    val value = step.item
    var right: TrainNode? = null
    var left: TrainNode? = null

    fun childCount() = when {
        right == null && left == null -> 0
        right != null && left != null -> 2
        else -> 1
    }

    fun nodeCountAt(distance: Int, root: TrainNode? = this): Int = when {
        root == null -> 0
        root.distance == distance -> 1
        else -> nodeCountAt(distance, root.left) + nodeCountAt(distance, root.right)
    }

    fun toStringTree(): String = "$this\nleft: {${left?.toStringTree()}}\tright: {${right?.toStringTree()}}"
    override fun toString() = "[$distance - $value]"
    override fun hashCode(): Int = this.value.hashCode() + this.left?.value.hashCode() + this.right?.value.hashCode()
    override fun equals(other: Any?): Boolean = this === other ||
            other is TrainNode && other.distance == this.distance && other.value == this.value
            && other.left == left && other.right == right
}
