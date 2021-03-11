package examples

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CashRegisterTest {

    @Test
    fun handleOneElementTest() {
        // Story 1: As a user I can enter "1 loaf" and see the price of $5.
        assertEquals(5, CashRegister().process("1 loaf"))
        // Story 2:  As a user I can enter "2 apples" and see the price of $6.
        assertEquals(6, CashRegister().process("2 apples"))
    }

    @Test
    fun handleMultipleElementsTest() {
        // Story 3: As a user I can enter "4 apples and 2 loaves" and see the price of $22.
        assertEquals(22, CashRegister().process("4 apples and 2 loaves"))
        // Story 4: As a user I can enter "2 loaves, 1 apple and 1 coke" and see the price of $15.
        assertEquals(15, CashRegister().process("2 loaves, 1 apple and 1 coke"))
    }

    @Test
    fun handleDeliveryTest() {
        // Story 5: As a user I can enter "2 loaves and 3 apples with delivery" and see the price of $29.
        assertEquals(29, CashRegister().process("2 loaves and 3 apples with delivery"))
        // Story 6: As a user I can enter "5 loaves, 4 apples and 3 cokes with delivery" and see the price of $56.
        // TODO: Make delivery based on the item
    }


}