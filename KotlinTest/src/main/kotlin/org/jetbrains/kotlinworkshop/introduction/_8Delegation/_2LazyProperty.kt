package org.jetbrains.kotlinworkshop.introduction._8Delegation

/**
 *
 * 代理属性的语法：
 *  val/var <property name>: <Type> by <expression>
 *
 *
 *
 * by lazy{} 只有第一次执行的时候，代码块中才执行。
 * 内部的实现是通过SynchronizedLazyImpl类。
 */
class LazyProperty {
    val lazy: Int by lazy {
        println("Calculate the value")
        42
    }
}

fun main() {
    println("--- creation ---")
    val property = LazyProperty()

    println("--- first access ---")
    property.lazy

    println("--- second access ---")
    property.lazy
}
//===>
// --- creation ---
// --- first access ---
// Calculate the value
// --- second access ---