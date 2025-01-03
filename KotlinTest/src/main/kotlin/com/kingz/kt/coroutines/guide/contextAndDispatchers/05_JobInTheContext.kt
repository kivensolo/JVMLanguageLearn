package com.kingz.kt.coroutines.guide.contextAndDispatchers

/**
 * author: King.Z <br>
 * date:  2020/7/2 11:19 <br>
 * description:
 * 上下文中的 Job
 *
 * 协程的 Job 是上下文的一部分，并且可以使用 coroutineContext [Job] 表达式在上下文中检索它： <br>
 */
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    println("My job is ${coroutineContext[Job]}")
}