package com.kingz.kt.coroutines.guide.composingSuspendingFunctions

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * author: King.Z <br>
 * date:  2020/7/1 17:25 <br>
 * description:  <br>
 */

fun main() = runBlocking<Unit> {
    invokeFunctionWithAsync()

    invokeFunctionWithLazyAsync()
}

/**
 *  使用 async 并发
 *  注意: 使用协程进行并发总是显式的
 */
private suspend fun invokeFunctionWithAsync() {
    coroutineScope {
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }
}

/**
 * 惰性启动 async
 * async 可以通过将 start 参数设置为 CoroutineStart.LAZY 而变为惰性的。
 * 在这个模式下，只有结果通过 await 获取的时候协程才会启动，或者在 Job 的 start 函数调用的时候。
 */
private suspend fun invokeFunctionWithLazyAsync() {
    coroutineScope {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) {
                doSomethingUsefulOne()
            }
            val two = async(start = CoroutineStart.LAZY) {
                doSomethingUsefulTwo()
            }
            // 定义后，协程并没有立即执行
            delay(2000L)
            // doSomething..... 执行一些计算

            println("Start async coroutine....")
            one.start() // 启动第一个
            two.start() // 启动第二个
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }
}