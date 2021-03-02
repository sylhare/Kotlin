package train

object TrainTree {
    fun indexesOf(line: List<TrainStep<TrainNetwork>>, index: Int = 0): List<Int> {
        var currentIndex = index
        val result = mutableListOf<Int>()
        val distances = line.map { it.distance }
        for (i in distances.indices) {
            val branchTwoRoot = distances.lastIndexOf(distances.getOrNull(i + 1))
            if (branchTwoRoot != -1 && branchTwoRoot != i + 1) {
                val branchOne = indexesOf(line.subList(i + 1, branchTwoRoot), index + 1)
                val rootStart = index + branchOne.size + 1
                val branchTwo = indexesOf(line.subList(branchTwoRoot, line.size), rootStart + 1)
                result += listOf(rootStart) + branchOne + branchTwo
                break
            } else result.add(currentIndex++)
        }
        return result
    }

    fun deserialize(line: List<TrainStep<TrainNetwork>>): TrainNode {
        var root: TrainNode? = null
        val indexes = indexesOf(line)
        line.withIndex().forEach { (i, step) -> root = insert(root, step, indexes[i]) }
        return root ?: TrainNode(0, TrainStep(0, RailSection.invalid))
    }

    private fun insert(root: TrainNode?, data: TrainStep<TrainNetwork>, index: Int): TrainNode =
        if (root == null) TrainNode(index, data) else when {
            index <= root.index -> root.also { it.left = insert(it.left, data, index) }
            else -> root.also { it.right = insert(it.right, data, index) }
        }
}