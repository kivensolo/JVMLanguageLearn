package com.kingz.kt.coroutines.guide.contextAndDispatchers

/**
 * author: King.Z <br>
 * date:  2020/7/2 11:44 <br>
 * description: 一个父协程总是等待所有的子协程执行结束。
 * 父协程并不显式的跟踪所有子协程的启动，并且不必使用 【Job.join】 在最后的时候等待子协程。 <br>
 */

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    // 在CoroutineScope范围中，启动一个协程来处理某种传入请求（parentJob）
    val parentJob = launch {
        repeat(3) { i -> // 启动少量的子Job
            launch  {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, 600ms
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
    parentJob.join() // 等待主协程 & 所以所有子协程 处理完毕
    println("Now processing of the request is complete")
}