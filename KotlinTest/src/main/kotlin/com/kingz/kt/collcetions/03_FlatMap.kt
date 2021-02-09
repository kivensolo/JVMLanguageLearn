package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/4 17:15 <br>
 * @desc: flatMap用于平铺集合元素
 */
//val result = listOf("abc", "12").flatMap { it.toCharList() }
//result == listOf('a', 'b', 'c', '1', '2')

// 返回消费者订购的所有产品集合
val Customer.orderedProducts:Set<Product> get(){
    return orders.flatMap {
        it.products   // 找到产品List的数据
    }.toSet()        // 转为Set
}