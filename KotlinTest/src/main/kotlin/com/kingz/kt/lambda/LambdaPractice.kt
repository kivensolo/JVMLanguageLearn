package com.kingz.kt.lambda


/**

 * @author: King.Z
 * @since: 2020/3/8 10:49 <br>
 * @desc: Lambda函数学习使用
 */

fun baseUse() {
    // Kotlin 使用类似  (Int) -> String  的一系列函数类型来处理函数的声明：
    // 所有函数类型都有一个圆括号括起来的参数类型列表以及一个返回类型： (A_KT, B) -> C

    // 1. 内外的都显示调用的lambda
    val upperCase1: (String, Int) -> String = {  //外
        // 内
        arg1: String, arg2: Int ->
        arg1.toUpperCase()
        arg2.toString()
        arg1 + arg2
    }

    // 2. 内部类型推断：会从其分配的变量类型来判断参数的类型
    val upperCase2: (String, Int) -> String = {
        // 内部类型推断
        str1, arg2 ->
        str1.toUpperCase()
        str1 + arg2.toString()
    }

    // 3. 外部类型推断
    val upperCase3 = { arg1: String, arg2: Int ->
        arg1.toUpperCase()
        arg1 + arg2.toShort()
        // 不能内外一起推断, 编译器不知道到底是什么类型
        // arg1 -> arg1.toUpperCase()
    }

    // 4. 具有单一参数的Lambda,可以使用隐式参数it
    val upperCase4: (String) -> String = {
        it.toUpperCase()
    }

    // 5. 若lambda由单个函数调用组成，则可以使用函数指针
    val upperCase5: (String) -> String = String::toUpperCase

    println(upperCase1("hello", 666))
    println(upperCase2("hello", 666))
    println(upperCase3("hello", 666))
    println(upperCase4("hello"))
    println(upperCase5("hello"))

}
fun main() {

    // 拖尾表达式   最后⼀个参数是函数时，可把函数放在圆括号之外
    val items = listOf(1, 2, 3, 4, 5)
    val accResult = items.fold(0) { acc, e ->
        val result = acc + e
        println("result = $result")
        result
    }

    //从 lambda 表达式中返回⼀个值
    val strings = listOf<String>("hello", "zeke", "Blue", "lol:", "Agent")
    // 加上拖尾表达式，可使用LINQ方式
    val endResult = strings.filter { it.length == 4 }.sortedBy { it }.map { it.toUpperCase() }
    println(endResult)

}
