package shopping

class CashRegister {

    companion object {
        val priceTags = mapOf("meatloaf" to 5, "pie" to 3, "soda" to 2)
    }

    // Natural language processing ~> latent dirichlet allocation?
    private val String.toSingular get() = when {
            this.endsWith("ves") -> this.trimEnd(*"ves".toCharArray()) + "f"
            else -> this.trimEnd('s')
        }

    private fun toPricedItem(item: String, quantity: Int) = when (quantity) {
        1 -> item
        else -> item.toSingular
    }

    fun process(item: String): Int {
        val previousLength = item.length
        val processedItems = item.replace(" with delivery", "")
        val deliveryFee = if (previousLength > processedItems.length) 10 else 0

        val request = processedItems.split(", ", " and ").map { it.split(" ") }
        return request.sumOf {
            it[0].toInt() * priceTags.getOrDefault(
                toPricedItem(it[1], it[0].toInt()),
                0
            )
        } + deliveryFee
    }

    fun bill(items: String) = Market().evaluate(items)
}
