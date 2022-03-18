import shopping.Ware

class Cart(input: String) {
    private val items: List<CartItem>
    private val hasDelivery: Boolean

    init {
        this.items = input.replace("with", ", 1")
            .split(", ", " and ")
            .map { it.toCartItem }
        this.hasDelivery = this.items.filter { it.isDelivery() }.size == 1
    }

    private val String.toCartItem get() = this.split(" ").let {
            CartItem(it[0].toInt(), it[1]).toSingular()
        }

    fun checkout(inventory: Map<String, Ware>): Int = this.items.sumOf {
        it.quantity * inventory.getOrDefault(it.name, Ware.unknown).price(hasDelivery)
    }

    private data class CartItem(val quantity: Int, var name: String) {
        fun toSingular() = when {
            this.quantity <= 1 -> this
            this.name.endsWith("ves") -> this.also { it.name = it.name.trimEnd(*"ves".toCharArray()) + "f" }
            else -> this.also { it.name = it.name.trimEnd('s') }
        }

        fun isDelivery(): Boolean = this.name == Ware.delivery.name
    }
}
