package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/5 22:54 <br>
 * @desc: Partition{} 是指按照某个条件分隔集合数据
 * 并将符合条件和不符合条件的数据集返回。
 * 就像做了两个groupBy
 */
fun main() {
    val numbers = listOf(1, 3, -4, 2, -11)
    val (positive, negative) = numbers.partition { it > 0 }
    println(positive)   // listOf(1, 3, 2)
    println(negative)   // listOf(-4, -11)

}
/*
* [1, 3, 2]
* [-4, -11]
* */

// 返回未交付订单多于已交付订单的顾客
fun Shop.getCustomersWithUndeliveredThanDelivered(): Set<Customer> {
    return customers.filter { customer ->
        // ---------- 过滤的条件
        val (delivered, undelivered) = customer.orders.partition {
            // ---------- partition的条件
            it.isDelivered
            // ---------- partition的条件
        }
        undelivered.size > delivered.size
        // ---------- 过滤的条件
    }.toSet()
}