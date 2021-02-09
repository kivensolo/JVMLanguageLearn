package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/4 16:57 <br>
 * @desc 对map进行条件过滤操作:
 */

// 返回消费者City的集合
fun Shop.getCitiesCustomersAreFrom(): Set<City> {
    // 使用map平铺数据
    return customers.map {
        it.city
    }.toSet()
}

// 找老乡
fun Shop.getCitiesCustomersFrom(city: City): List<Customer> {
    return customers.filter {
        it.city == city
    }
}

//fun Shop.getCitiesCustomersFrom(city: City): List<Customer> = customers.filter {
//        it.city == city
//    }

val numbers = listOf(1, -1, 2)
fun main() {
    println(numbers.filter { it > 0 })  // [1, 2]
    println(numbers.map { it * it })    // [1, 1, 4]
}