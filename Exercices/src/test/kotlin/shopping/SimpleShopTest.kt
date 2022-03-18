package shopping

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SimpleShopTest {

    @Test
    fun priceForOneItem() {
        assertEquals(5, SimpleShop().process("1 meatloaf"))
    }

    @Test
    fun handleDifferentQuantity() {
        assertEquals(6, SimpleShop().process("2 pies"))
    }

    @Test
    fun detectMultipleElementsWithAnd() {
        assertEquals(22, SimpleShop().process("4 pies and 2 meatloaves"))
    }

    @Test
    fun detectMultipleElementsWithComma() {
        assertEquals(15, SimpleShop().process("2 meatloaves, 1 pie and 1 soda"))
    }

    @Test
    fun handleDeliveryTest() {
        assertEquals(29, SimpleShop().process("2 meatloaves and 3 pies with delivery"))
    }
}
