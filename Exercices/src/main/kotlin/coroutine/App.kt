package coroutine

import kotlinx.coroutines.*
import java.util.*

internal class App {
    private var listen = false
    private var dispatcher = Dispatchers.IO
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun start() {
        scope.launch(dispatcher) {
            bootstrap()
            println("App is running ...")
            println("App is listening: $listen")
        }
    }

    companion object {
        fun appWithDispatcher(dispatcher: CoroutineDispatcher): App {
            val app = App()
            app.dispatcher = dispatcher
            return app
        }
    }

    fun isListening() = listen
    private fun init(properties: Properties) = println("Initialising App with $properties")
    private suspend fun bootstrap() = init(loadProperties())
    private suspend fun loadProperties(): Properties = withContext(Dispatchers.IO) {
        val properties = Properties()
        properties.load(ClassLoader.getSystemResourceAsStream("${System.getProperty("properties")}.properties"))
        listen = true
        properties
    }
}

