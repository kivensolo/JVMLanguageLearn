package com.kingz.kt.coroutines.guide.composingSuspendingFunctions

/**
 * author: King.Z <br>
 * date:  2020/7/1 18:00 <br>
 * description:  <br>
 */
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 模拟并发任务时的异常情况
 * 取消始终通过协程的层次结构来进行传递
 */

fun main() = runBlocking<Unit> {
    try {
        failedConcurrentSum()
    } catch(e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // 模拟一个长时间的运算
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
        // v1.3.72版本中，其中一个子协程（即 two）失败，第一个 async 以及等待中的父协程都会被取消。
        // 但是实际运行的时候，发现第一个async的任务还在执行，并没有被取消。
    }
    one.await() + two.await()
}