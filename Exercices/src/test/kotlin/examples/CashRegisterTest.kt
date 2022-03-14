package examples

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class CashRegisterTest {

    @Test
    fun priceForOneItem() {
        assertEquals(5, CashRegister().process("1 meatloaf"))
    }

    @Test
    fun handleDifferentQuantity() {
        assertEquals(6, CashRegister().process("2 pies"))
    }

    @Test
    fun detectMultipleElementsWithAnd() {
        assertEquals(22, CashRegister().process("4 pies and 2 meatloaves"))
    }

    @Test
    fun detectMultipleElementsWithComma() {
        assertEquals(15, CashRegister().process("2 meatloaves, 1 pie and 1 soda"))
    }

    @Test
    fun handleDeliveryTest() {
        assertEquals(29, CashRegister().process("2 meatloaves and 3 pies with delivery"))
    }

    @Test
    @Disabled
    fun handlePremiumOnDeliveryFees() {
        assertEquals(56, CashRegister().process("5 meatloaves, 4 pies and 3 sodas with delivery"))
    }
}
