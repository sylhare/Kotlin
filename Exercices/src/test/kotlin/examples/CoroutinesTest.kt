package examples

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CoroutinesTest {

    @Test
    fun `should add world async`() {
        // given
        val result = mutableListOf<String>()

        // when
        runBlocking {
            val promise = launch {
                Coroutines.expensiveComputation(result)
            }
            result.add("Hello,")
            promise.join()
        }

        // then
        assertEquals(listOf("Hello,", "word!"), result)
    }

    @Test
    fun `does not add because of delays`() {
        val result = mutableListOf<String>()
        val c = Coroutines()
        runBlocking {
            val promise = launch {
                Coroutines.expensiveComputation(result)
            }
            c.helloWorld()
            result.add("Hello,")
            promise.join()
        }
        assertEquals(listOf("word!", "Hello,"), result)
    }
}