import shopping.Ware

private val inventory: List<Ware> = listOf(
    Ware.delivery,
    Ware.unknown,
    Ware.soda,
    Ware.meatloaf,
    Ware.pie
)

data class Market(
    private val name: String = "Market",
    private val items: Map<String, Ware> = inventory.associateBy { it.name }
) {
    fun price(cart: Cart): Int = cart.checkout(items)
}

class SuperMarket {
    private val market = Market("Kwik-E-Mart")

    fun process(input: String) = market.price(Cart(input))
}
