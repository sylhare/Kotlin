package coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@kotlinx.coroutines.ExperimentalCoroutinesApi
internal class AppTest {

    private val testDispacher = TestCoroutineDispatcher()

    @Test
    fun startAppTest() {
        System.getProperties()["properties"] = "example"
        val app = App()
        app.start()
        Thread.sleep(100)
        assertTrue(app.isListening())

    }

    @Test
    fun startAppWithRunBlockingTest() {
        runBlocking {
            System.getProperties()["properties"] = "example"
            val app = App()
            app.start()
            delay(100)
            assertTrue(app.isListening())
        }
    }

    @Test
    fun startAppWithTestRunBlockingTest() {
        System.getProperties()["properties"] = "example"
        val app = App.appWithDispatcher(testDispacher)
        testDispacher.runBlockingTest {
            launch { app.start() }.join()
            delay(100)
        }
        assertTrue(app.isListening())
    }


    @Test
    fun testDispatcherTest() {
        testDispacher.runBlockingTest {
            var late = "late"
            GlobalScope.launch(testDispacher) {
                delay(100)
                late = "on time"
            }.join()
            assertEquals("on time", late)
        }
    }
}