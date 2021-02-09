package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/4 16:51 <br>
 * @desc:
 */

// products
val idea = Product("Intellij IDEA Ultimate", 199.0)
val pyCharm = Product("PyCharm", 99.0)
val webStorm = Product("@WebStorm", 49.0)

// customers
val lucas = "Lucas"
val cooper = "Cooper"
val asuka = "asuka"

// Cities
val Chengdu = City("Chengdu")
val BeiJing = City("BeiJing")
val Tokyo = City("Tokey")

// 创建消费者对象
fun customer(name: String, city: City, vararg orders: Order): Customer {
    return Customer(name, city, orders.toList())  // 集合的转换通常是toXXX
}

fun order(vararg products: Product, isDelivered: Boolean = true): Order {
    return Order(products.toList(), isDelivered)
}

fun shop(name: String, vararg customers: Customer): Shop {
    return Shop(name, customers.toList())
}


val shop = shop("JD shop",
        customer(lucas, Chengdu,
                order(idea),
                order(idea,pyCharm,webStorm)),
        customer(cooper, Tokyo),
        customer(asuka, BeiJing,
                order(pyCharm,webStorm)))

val customers: Map<String, Customer> = shop.customers.fold(hashMapOf<String,Customer>(),{
    map, customer ->
    map[customer.name] = customer
    map
})


val orderedProducts = setOf(idea, pyCharm, webStorm)
val sortedCustomer = listOf(cooper, lucas, asuka)

fun main(){
    val richMan = shop.getCustomerWithMaximumNumberOfOrders()
    println("订单总数最多的客户是：${richMan?.name}")
    println("他买的价格最贵的产品是：${richMan?.getMostExpensiveOrderedProduct()}")
}

