package train

data class TrainStep<T>(val distance: Int, val item: T)
sealed class TrainNetwork
data class RailSection(private val info: String, val position: Int? = null) : TrainNetwork() {
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
data class Station(private val info: String) : TrainConnection() {
    override fun toString() = "station [$info]"
}

val String.switch get()  = Switch(this)
val String.railSection get()  = RailSection(this, this.filter { it.isDigit() }.toIntOrNull())
val Int.junction get() = Junction(this)
val String.station get() = Station(this)