package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/4 18:03 <br>
 * @desc:
 */
fun main() {
    println(listOf(1, 5, 3).sum())  // == 9
    println(listOf(1.23, 5.2, 3.3).sumByDouble { it })  // == 9.73
    println(listOf("a", "b", "cc").sumBy{ it.length })  // == 4
}