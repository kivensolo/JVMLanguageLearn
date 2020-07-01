package com.kingz.kt.coroutines.guide.composingSuspendingFunctions

/**
 * author: King.Z <br>
 * date:  2020/7/1 16:49 <br>
 * description: 介绍挂起函数组合的默认顺序调用<br>
 * 像常规的代码一样 顺序 都是默认的。
 *
 *   假设在不同的地方定义了两个进行某种调用远程服务或者进行计算的挂起函数。
 * 只假设它们都是有用的，但是实际上它们在这个示例中只是为了该目的而延迟了一秒钟：
 */

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    invokeFunctionDefault()
}

/**
 *  默认执行顺序
 */
private suspend fun invokeFunctionDefault() {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}


suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}