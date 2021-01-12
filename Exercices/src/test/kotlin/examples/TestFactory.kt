package examples

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class TestFactory {

    @TestFactory
    fun multiplyDoubleTest() = listOf(
        listOf(0.0, 0.0, 0.0),
        listOf(0.0, 5.0, 0.0),
        listOf(1.0, 1.0, 1.0),
        listOf(1.0, 6.0, 6.0),
        listOf(-2.0, 3.0, -6.0),
        listOf(-2.0, 0.0, 0.0),
        listOf(2.0, 0.5, 1.0),
        listOf(2.0, 6.0, 12.0)
    ).map { (a, b, expectedResult) ->
        DynamicTest.dynamicTest("Test:  $a x $b =  $expectedResult") {
            assertEquals(expectedResult, multiply(a, b))
        }
    }

    @TestFactory
    fun multiplyIntTest() = listOf(
        listOf(0, 0, 0),
        listOf(1, 0, 0),
        listOf(1, 4, 4),
        listOf(2, -4, -8),
        listOf(2, 5, 10)
    ).map { (a, b, expectedResult) ->
        DynamicTest.dynamicTest("Test:  $a x $b =  $expectedResult") {
            assertEquals(expectedResult, multiply(a, b).toInt())
        }
    }

    @TestFactory
    fun multiplyWithInstructionBeforeTest(): List<DynamicTest> {
        val zero = 0.0
        return listOf(
            listOf(zero, 0.0, zero),
            listOf(zero, 1.0, zero)
        ).map { (a, b, expectedResult) ->
            DynamicTest.dynamicTest("Test:  $a x $b =  $expectedResult") {
                assertEquals(expectedResult, multiply(a, b))
            }
        }
    }
}