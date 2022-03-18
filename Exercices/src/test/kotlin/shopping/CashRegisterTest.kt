package shopping

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import shopping.CashRegister

internal class CashRegisterTest {

    @Test
    fun priceForOneItem() {
        assertEquals(5, CashRegister().process("1 meatloaf"))
        assertEquals(5, CashRegister().bill("1 meatloaf"))
    }

    @Test
    fun handleDifferentQuantity() {
        assertEquals(6, CashRegister().process("2 pies"))
        assertEquals(6, CashRegister().bill("2 pies"))
    }

    @Test
    fun detectMultipleElementsWithAnd() {
        assertEquals(22, CashRegister().process("4 pies and 2 meatloaves"))
        assertEquals(22, CashRegister().bill("4 pies and 2 meatloaves"))
    }

    @Test
    fun detectMultipleElementsWithComma() {
        assertEquals(15, CashRegister().process("2 meatloaves, 1 pie and 1 soda"))
        assertEquals(15, CashRegister().bill("2 meatloaves, 1 pie and 1 soda"))
    }

    @Test
    fun handleDeliveryTest() {
        assertEquals(29, CashRegister().process("2 meatloaves and 3 pies with delivery"))
        assertEquals(29, CashRegister().bill("2 meatloaves and 3 pies with delivery"))
    }

    @Test
    fun handlePremiumOnDeliveryFees() {
        assertEquals(56, CashRegister().bill("5 meatloaves, 4 pies and 3 sodas with delivery"))
    }
}
