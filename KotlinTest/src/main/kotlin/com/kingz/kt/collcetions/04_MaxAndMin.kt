package com.kingz.kt.collcetions

import kotlin.math.abs

/**

 * @author: King.Z
 * @since: 2020/7/4 17:20 <br>
 * @desc:
 *
 * max : 返回最大的元素，如果没有元素，返回“null”
 * min : 返回最小的元素，如果没有元素，返回“null”
 * maxBy : 返回给定函数的最大值的第一个元素，如果没有元素，则返回“null”
 * minBy : 返回给定函数的最小值的第一个元素，如果没有元素，则返回“null”
 */
fun main() {
    println(listOf(1, 42, 4).max())  // == 42
    println(listOf(1, 42, 4).maxBy{ abs(it - 42) })  // == 1
    println(listOf("a", "ab").minBy { it.length }) // == "a"
}

// 返回订单总数最多的客户
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = customers.maxBy {
    it.orders.size
}

// 返回用户买的价格最贵的产品
fun Customer.getMostExpensiveOrderedProduct(): Product? = orders.flatMap {
    it.products
}.maxBy {
    it.price
}

