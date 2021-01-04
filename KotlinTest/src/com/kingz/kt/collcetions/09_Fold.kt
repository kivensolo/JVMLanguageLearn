package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/5 23:17 <br>
 * @desc:
 *
 *  fun <T, R> kotlin.collections.Iterable<T>.fold(
 *  initial: R,
 *  operation: (R, T) -> R): R
 *
 *  initial为临时数据，用于operation的第一个参数默认值。
 *  operation函数是对集合元素进行自定义操作处理，处理后的结果，更新为R，直到集合循环完毕。
 */

fun main() {
    println(listOf(1, 2, 3, 4).fold(1, { partProduct, element ->
        element * partProduct
    }))
}

// 返回每个客户订购的产品集
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
    val allProducts = customers.flatMap{customer ->
        customer.orders.flatMap { orderItem -> orderItem.products }
    }.toSet()

    return  customers.fold(allProducts) {
        orderedByAll, customer ->
        orderedByAll.intersect(customer.orders.flatMap {
            it.products
        }.toSet())
    }
}
