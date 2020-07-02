package com.kingz.kt.coroutines.guide.contextAndDispatchers

/**
 * author: King.Z <br>
 * date:  2020/7/2 11:36 <br>
 * description:
 *     当一个协程被其它协程在 【CoroutineScope】 中启动的时候， 它将通过 【CoroutineScope.coroutineContext】 来继承上下文，
 * 并且这个新协程的 Job 将会成为父协程Job的子Job。
 * 当一个父协程被取消的时候，所有它的子协程也会被递归的取消。<br>
 *     然而，当使用 【GlobalScope】 来启动一个协程时，则新协程的作业没有父作业。 因此它与这个启动的作用域无关且独立运作。
 */
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    // 启动一个协程来处理某种传入请求（request）
    val request = launch {
        // 启动A、B两个协程

        // A : 通过 GlobalScope 启动, 新的协程没有父Job，所以它与启动它的作用域无关，且独立运行。
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(2000)
            println("job1: I am not affected by cancellation of the request")
        }

        // B : 集成父协程的Context
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(2000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    println("main: Do cancel action for jobs.")
    request.cancel() // 取消请求（request）的执行
    delay(2000) // 延迟2秒钟来看看发生了什么
    println("main: Who has survived request cancellation?")
}