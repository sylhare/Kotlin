package train

internal typealias TrainLine = MutableList<TrainStep<TrainNetwork>>

sealed class TrainNetwork(open val info: String? = null)
sealed class TrainConnection : TrainNetwork()
data class TrainStep<T>(val distance: Int, val item: T)

data class RailSection(override val info: String, val position: Int? = null) : TrainNetwork() {
    companion object {
        val invalid = "trunk invalid".railSection
    }

    override fun toString() = when {
        this.position != null -> "section [$info - $position]"
        else -> "section [$info]"
    }
}

data class Switch(override val info: String) : TrainNetwork() {
    companion object {
        val invalid = "trunk invalid".switch
        const val ERROR = "Errored Switch"
    }

    override fun toString() = "switch [$info]"
}
data class Junction(val position: Int) : TrainConnection() {
    override fun toString() = "junction [$position]"
}
data class Station(override val info: String) : TrainConnection() {
    override fun toString() = "station [$info]"
}

val String.switch get()  = Switch(this)
val String.railSection get()  = RailSection(this, this.filter { it.isDigit() }.toIntOrNull())
val Int.junction get() = Junction(this)
val String.junction get() = Junction(this.filter { it.isDigit() }.toInt())
val String.station get() = Station(this)
fun trainLineOf(vararg step: TrainStep<TrainNetwork>): TrainLine = step.toMutableList()
