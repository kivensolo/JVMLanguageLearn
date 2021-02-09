package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/4 16:03 <br>
 * @desc:
 */
data class Shop(val name: String, val customers: List<Customer>)

data class Customer(val name: String, val city: City, val orders: List<Order>) {
    override fun toString(): String {
        return "$name from ${city.name}"
    }
}

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Product(val name: String, val price: Double) {
    override fun toString() = "'$name' for $price"
}

data class City(val name: String) {
    override fun toString() = name
}
