package com.kingz.kt.collcetions

/**

 * @author: King.Z
 * @since: 2020/7/4 17:07 <br>
 * @desc:
 * 常用的 predicates
 * all, any, count, find.
 */
// Example
val isZero: (Int) -> Boolean = { it == 0 }
fun main(){
    // any 集合中是否有任意元素符合条件
    println(numbers.any(isZero)) // true
    // all 集合中是否所有元素都符合条件
    println(numbers.all(isZero)) // false
    // count 集合中符合条件的元素个数
    println(numbers.count(isZero)) //  1
    // find  查找集合中符合条件的元素个数
    println(numbers.find { it > 0 }) // 2
}

