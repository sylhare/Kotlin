package shopping

data class PricedItems(
    val name: String,
    val cost: Int = 0,
    val deliveryPremium: Int = 0
) {
    companion object {
        val delivery = PricedItems("delivery", 10, 0)
        val soda = PricedItems("soda", 2, 1)
        val meatloaf = PricedItems("meatloaf", 5, 0)
        val pie = PricedItems("pie", 3, 0)
        val unknown = PricedItems("", 0, 0)
    }

    fun price(hasDelivery: Boolean) = when (hasDelivery) {
        true -> this.cost + this.deliveryPremium
        else -> this.cost
    }
}

private val inventory: List<PricedItems> = listOf(
    PricedItems.delivery,
    PricedItems.unknown,
    PricedItems.soda,
    PricedItems.meatloaf,
    PricedItems.pie
)

data class Market(private val items: Map<String, PricedItems> = inventory.associateBy { it.name }) {

    fun evaluate(items: String) = this.price(items
        .replace("with", ", 1")
        .split(", ", " and ")
        .map { CartItem(it) })

    private fun price(cart: List<CartItem>): Int {
        val hasDelivery = cart.filter { it.name == PricedItems.delivery.name }.size == 1
        return cart.map { it.toSingular }
            .sumOf { it.quantity * this.items.getOrDefault(it.name, PricedItems.unknown).price(hasDelivery) }
    }

    private val CartItem.toSingular
        get() = when {
            this.quantity <= 1 -> this
            this.name.endsWith("ves") -> this.also { it.name = it.name.trimEnd(*"ves".toCharArray()) + "f" }
            else -> this.also { it.name = it.name.trimEnd('s') }
        }
}

class CartItem(item: String) {
    val quantity: Int
    var name: String

    init {
        item.split(" ").also {
            this.quantity = it[0].toInt()
            this.name = it[1]
        }
    }
}
