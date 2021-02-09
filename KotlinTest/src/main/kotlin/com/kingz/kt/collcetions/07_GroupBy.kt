package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/5 22:51 <br>
 * @desc: 通过 groupBy{ group的条件 } 对集合元素进行分组
 *
 * 返回值是Map<K, List<T>>
 *     K为进行条件分组的条件，List<T> 为符合条件的数据集合
 */
fun main() {
    val result = listOf("a", "b", "ba", "ccc", "ad").groupBy { it.length }
    // 等于mapOf(1 to listOf("a", "b"), 2 to listOf("ba", "ad"), 3 to listOf("ccc"))
    println(result)
}

// {1=[a, b], 2=[ba, ad], 3=[ccc]}


/**
 * 返回每个城市的客户的Map
 */
fun Shop.groupCustomerByCity(): Map<City, List<Customer>> {
    return customers.groupBy {
        it.city
    }
}