package shopping

data class Ware(val name: String, val cost: Int = 0, val deliveryPremium: Int = 0) {
    companion object {
        val delivery = Ware("delivery", 10)
        val soda = Ware("soda", 2, 1)
        val meatloaf = Ware("meatloaf", 5)
        val pie = Ware("pie", 3)
        val unknown = Ware("")
    }

    fun price(hasDelivery: Boolean) = when (hasDelivery) {
        true -> this.cost + this.deliveryPremium
        else -> this.cost
    }
}
