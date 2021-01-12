package examples

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class FunctionalTest {

    private lateinit var functional : Functional

    @BeforeEach
    fun setup() {
        functional = Functional()
    }

    @Test
    fun applyThisTest() {
        assertEquals(0, functional.a)
        assertEquals(0, functional.counter)
        assertEquals(0, functional.applyThis())
        assertEquals(0, functional.a)
        assertEquals(1, functional.counter)
    }

    @Test
    fun letItTest() {
        assertEquals(0, functional.a)
        assertEquals(0, functional.counter)
        assertEquals(3, functional.letIt())
        assertEquals(0, functional.a)
        assertEquals(1, functional.counter)
    }

    @Test
    fun alsoItTest() {
        assertEquals(0, functional.a)
        assertEquals(0, functional.counter)
        assertEquals(0, functional.alsoIt())
        assertEquals(0, functional.a)
        assertEquals(1, functional.counter)
    }

    @Test
    fun runThisTest() {
        assertEquals(0, functional.a)
        assertEquals(0, functional.counter)
        assertEquals(2, functional.runThis())
        assertEquals(0, functional.a)
        assertEquals(1, functional.counter)
    }

    @Test
    fun withThisTest() {
        assertEquals(0, functional.a)
        assertEquals(0, functional.counter)
        assertEquals(2, functional.withThis())
        assertEquals(0, functional.a)
        assertEquals(1, functional.counter)
    }

    @Test
    fun applyThis2Test() {
        assertEquals(0, functional.a)
        assertEquals(0, functional.applyThisInstantiate())
        assertEquals(0, functional.a)
    }

    @Test
    fun applyThis3Test() {
        assertEquals(0, functional.a)
        assertEquals(3, functional.applyThisReturn())
        assertEquals(0, functional.a)
    }

    @Test
    fun letIt2Test() {
        assertEquals(0, functional.a)
        assertEquals(Unit, functional.letItInstantiate())
        assertEquals(1, functional.a)
    }

    @Test
    fun alsoIt2Test() {
        assertEquals(0, functional.a)
        assertEquals(0, functional.alsoItInstantiate())
        assertEquals(1, functional.a)
    }

    @Test
    fun alsoIt3Test() {
        assertEquals(0, functional.a)
        assertEquals(2, functional.alsoItReturn())
        assertEquals(0, functional.a)
    }

    @Test
    fun runThis2Test() {
        assertEquals(0, functional.a)
        assertEquals(Unit, functional.runThisInstantiate())
        assertEquals(1, functional.a)
    }
}