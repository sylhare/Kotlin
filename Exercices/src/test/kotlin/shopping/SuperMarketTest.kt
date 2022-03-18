package shopping

import SuperMarket
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SuperMarketTest {
    private val market = SuperMarket()

    @Test
    fun priceForOneItem() {
        assertEquals(5, market.process("1 meatloaf"))
    }

    @Test
    fun handleDifferentQuantity() {
        assertEquals(6, market.process("2 pies"))
    }

    @Test
    fun detectMultipleElementsWithAnd() {
        assertEquals(22, market.process("4 pies and 2 meatloaves"))
    }

    @Test
    fun detectMultipleElementsWithComma() {
        assertEquals(15, market.process("2 meatloaves, 1 pie and 1 soda"))
    }

    @Test
    fun handleDeliveryTest() {
        assertEquals(29, market.process("2 meatloaves and 3 pies with delivery"))
    }

    @Test
    fun handlePremiumOnDeliveryFees() {
        assertEquals(56, market.process("5 meatloaves, 4 pies and 3 sodas with delivery"))
    }
}
