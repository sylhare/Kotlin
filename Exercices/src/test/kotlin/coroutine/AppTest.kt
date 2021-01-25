package coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertArrayEquals
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
    fun dispatcherTest() {
        testDispacher.runBlockingTest {
            var late = "late"
            GlobalScope.launch(testDispacher) {
                delay(100)
                late = "on time"
            }.join()
            assertEquals("on time", late)
        }
    }

    @Test
    fun listInPartitionTest() {
        val listOfObj = listOf("B", "B" , "S", "B", "B", "X", "S", "B", "B", "P")
        val result = mutableListOf<List<String>>()
        var current = mutableListOf<String>()
        listOfObj.forEach { letter ->
            if (letter == "S") {
                result.add(current)
                current = mutableListOf()
            }
            current.add(letter)
        }
        if (current.isNotEmpty()) {
            result.add(current)
        }
        assertEquals(result, listOf(listOf("B", "B"), listOf("S", "B", "B", "X"), listOf("S", "B", "B", "P")))
    }

    @Test
    fun breakForTest() {
        var i = 1
        var j = 2

        println(j + i++)
        i = 1
        println(j + ++i)

        var a = 1
        var b = 1
        b += 1
        a++
        assertEquals(a, b)

        for (i in 0..10) {
            print(i)
            for (letter in 'a'..'z') {
                if (letter == 'c') break
             }
        }

        for (e in 0..3) {
            loop@ while (i < 100) {
                when {
                    i % 9 == 0 -> {
                        println("break")
                        break
                    }
                    i % 25 == 0 -> {
                        println("break@loop")
                        break@loop
                    }
                }
                i++
            }
        }

        val numbers = listOf("one", "two", "three", "four", "five", "six")

        var index = numbers.indexOfFirst { it == "five" }
        var indexT = numbers.takeLast(3).indexOfFirst { it == "five" }
        assertEquals(1, indexT)
        val aObj = A()
        aObj.i++
        assertEquals(2, aObj.i)



    }

    class A {
        var i = 1
    }
}