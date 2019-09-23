package hello

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
}