package examples

class CashRegister {

    companion object {
        val priceTags = mapOf("loaf" to 5, "apple" to 3, "coke" to 2)
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

        val request = processedItems.replace(", ", " and ").split(" and ").map { it.split(" ") }
        return request.map { it[0].toInt() * priceTags.getOrDefault(toPricedItem(it[1], it[0].toInt()), 0) }.sum() + deliveryFee
    }
}