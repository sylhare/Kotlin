package train

data class TrainStep<T>(val distance: Int, val item: T) {
    override fun toString() = "step{$distance - $item}"
}

sealed class TrainNetwork
data class RailSection(val info: String, val position: Int? = null) : TrainNetwork() {
    companion object {
        val invalid = "trunk invalid".railSection
    }

    override fun toString() = "section [$info - $position]"
}
data class Switch(val info: String) : TrainNetwork() {
    override fun toString() = "switch [$info]"
}

sealed class TrainConnection : TrainNetwork()
data class Junction(val position: Int) : TrainConnection() {
    override fun toString() = "junction [$position]"
}
data class Station(val info: String) : TrainConnection() {
    override fun toString() = "station [$info]"
}

val String.switch get()  = Switch(this)
val String.railSection get()  = RailSection(this, this.filter { it.isDigit() }.toIntOrNull())
val Int.junction get() = Junction(this)
val String.station get() = Station(this)