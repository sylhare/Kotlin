package train

data class TrainStep<T>(val distance: Int, val item: T)

sealed class TrainNetwork
data class RailSection(val info: String, val position: Int? = null) : TrainNetwork()
data class Switch(val info: String) : TrainNetwork()

sealed class ClientConnection : TrainNetwork()
data class Junction(val position: Int) : ClientConnection()
data class Station(val info: String) : ClientConnection()

val String.switch get()  = Switch(this)
val String.railSection get()  = RailSection(this, this.filter { it.isDigit() }.toIntOrNull())
val Int.junction get() = Junction(this)
val String.station get() = Station(this)