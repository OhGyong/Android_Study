# ``` kotlin
open class Item(val name: String, val price: Int){

}

class Noodles : Item("Noodles", 10){
    override fun toString(): String{
        return name
    }
}

class Vegetables(vararg val toppings: String) : Item("Vegetables", 5){
    override fun toString(): String {
        if (toppings.isEmpty()) {
            return "$name Chef's Choice"
        } else {
            return name + " " + toppings.joinToString()
        }
    }
}

class Order(val orderNumber: Int) {
    private val itemList = mutableListOf<Item>()

    // 단일 항목 추가
    fun addItem(newItem: Item): Order {
        itemList.add(newItem)
    	return this
    }

    // 여러 항목 추가
    fun addAll(newItems: List<Item>): Order {
        itemList.addAll(newItems)
        return this
    }

    // 주문 요약 출력
    fun print() {
        println("Order #${orderNumber}")
        var total = 0
        for (item in itemList) {
            println("${item}: $${item.price}")
            total += item.price
        }
        println("Total: $${total}")
    }
}

fun main() {
    val ordersList = mutableListOf<Order>()

    val order1 = Order(1)
    order1.addItem(Noodles())
    ordersList.add(order1)

    val order2 = Order(2)
    order2.addItem(Noodles())
    order2.addItem(Vegetables())
    ordersList.add(order2)

    val order3 = Order(3)
    val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
    order3.addAll(items)
    ordersList.add(order3)
    
    val order4 = Order(4).addItem(Noodles()).addItem(Vegetables("Cabbage", "Onion"))
    ordersList.add(order4)
    
    ordersList.add(
    Order(5)
        .addItem(Noodles())
        .addItem(Noodles())
        .addItem(Vegetables("Spinach")))

    for (order in ordersList) {
        order.print()
        println()
    }
}
```