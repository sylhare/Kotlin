package coroutine

import kotlinx.coroutines.*
import java.util.*

// https://www.youtube.com/watch?v=ZTDXo0-SKuU&feature=youtu.be
class App(private val dispatcher: CoroutineDispatcher) {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun start() {
        scope.launch(dispatcher) {
            bootstrap()
            print("App is running ...")
        }
    }

    private fun init(properties: Properties) = print("Initialising App with $properties")
    private suspend fun bootstrap() = init(properties())
    private suspend fun properties(): Properties = withContext(Dispatchers.IO) {
        val properties = Properties()
        properties.load(ClassLoader.getSystemResourceAsStream("${System.getProperty("properties")}.properties"))
        properties
    }

    @ExperimentalCoroutinesApi
    private fun stop() = scope.cancel()


}