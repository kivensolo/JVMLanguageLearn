package com.kingz.kt.collcetions

/**
 * 集合的元素操作符
 * groupBy：按照闭包条件分组
 *
 * slice ：按照入参将集合切分
 *
 * take：从前往后拿取集合中的前n个元素，n是入参
 * takeLast：从后往前拿取集合中的前n个元素，n是入参
 *
 * takeWhile：按照lambda表达式条件，从前往后拿取元素，直到第一个不符合条件的元素出现为止，返回符合条件的元素列表。
 * takeLastWhile：按照lambda表达式条件，从后往前拿取元素，直到第一个不符合条件的元素出现为止，返回符合条件的元素列表。
 *
 * drop：从前往后丢弃集合中的前n个元素，n是入参
 * dropLast：从后往前丢弃集合中的前n个元素，n是入参
 *
 * dropWhile：按照lambda表达式条件，从前往后丢弃元素，直到第一个不符合条件的元素出现为止
 * dropLastWhile：按照lambda表达式条件，从后往前丢弃元素，直到第一个不符合条件的元素出现为止
 *
 * first：返回符合条件的第一个元素，没有不返回任何内容
 * firstOrNull：返回符合条件的第一个元素，没有返回null
 *
 * elementAt：返回对应的元素，越界会抛IndexOutOfBoundsException
 * elementAtOrNull：返回对应的元素，越界返回null
 * elementAtOrElse：返回对应的元素，越界则执行lambda表达式
 * find：同firstOrNull
 * findLast：同lastOrNull
 * contains：判断是否有指定元素
 * containsAll：判断是否包含指定的元素集
 *
 * chunked：将集合按照n分块，n是入参，被分块的元素不在参与下一次分块
 * windowed：将集合按照n分块，n是入参，被分块的元素可能继续参与下一次分块，类似滑动窗口
 * zipWithNext：将集合两两一组切分
 */

fun main() {


    //groupBy 按照闭包条件分组
    val result = listOf("a", "b", "ba", "ccc", "ad").groupBy { it.length }
    // 等于mapOf(1 to listOf("a", "b"), 2 to listOf("ba", "ad"), 3 to listOf("ccc"))
    println(result) // {1=[a, b], 2=[ba, ad], 3=[ccc]}


    println("-------------------take-------------------")
    val numbers = listOf(4, 9, 1, 3, 2, 5, 3, 4, 5, 6, 7, 8)
    //从前往后拿取集合中的前n个元素 [4,9,1,3]
    println(numbers.take(4))
    println(numbers.takeIf {
        return@takeIf it[0] > 10
    })
    //从前往后拿取元素，直到第一个不符合lambda表达式条件的元素为止  [4,9]
    println(numbers.takeWhile { it > 3 })
    //从后往前拿取元素，直到第一个不符合lambda表达式条件的元素为止  [4, 5, 6, 7, 8]
    println(numbers.takeLastWhile { it > 3 })

    println("-------------------drop-------------------")
    //从前往后丢弃集合中的前n个元素  [2, 5, 3, 4, 5, 6, 7, 8]
    println(numbers.drop(4))
    //从前往后丢弃元素，直到第一个不符合lambda表达式条件的元素出现为止 [1, 3, 2, 5, 3, 4, 5, 6, 7, 8]
    println(numbers.dropWhile { it > 3 })
    //从后往前丢弃元素，直到第一个不符合lambda表达式条件的元素出现为止 [4, 9, 1, 3, 2, 5, 3]
    println(numbers.dropLastWhile { it > 3 })
}




/**
 * 返回每个城市的客户的Map
 */
fun Shop.groupCustomerByCity(): Map<City, List<Customer>> {
    return customers.groupBy {
        it.city
    }
}