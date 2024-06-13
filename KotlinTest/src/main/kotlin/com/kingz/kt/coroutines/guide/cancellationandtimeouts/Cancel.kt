package com.kingz.kt.coroutines.guide.cancellationandtimeouts

import kotlinx.coroutines.*
import java.lang.System.currentTimeMillis

/**
 * author: King.Z <br>
 * date:  2020/7/1 11:43 <br>
 * description: 在一个长时间运行的应用程序中，你也许需要对你的后台协程进行细粒度的控制。
 * 比如说，一个用户也许关闭了一个启动了协程的界面，那么现在协程的执行结果已经不再被需要了，
 * 这时，它应该是可以被取消的。
 * launch() 函数返回了一个可以被用来取消运行中的协程的 Job： <br>
 */

fun main() = runBlocking {
    // 1. 取消协程的执行
//    cancelJob()

    // 2.取消是协作的
//    cancelJobWithNoCheck()

    // 3.使计算代码可取消
//    cancelJobWithCheck()

    // 4.在 finally 中释放资源
    cancelJobWithFinally()

    // 5.运行不能取消的代码块
    println("")
    cancelWithContext()
}

private suspend fun CoroutineScope.cancelJob() {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm repeating $i ...")
            delay(500L)
        }
    }
    println("org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main: wait job.")
    delay(1300L)
    println("org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main: I'm tired of waiting!")
    job.cancel() // 取消该job
    job.join()   // 等待job执行结束，阻塞主线程
//    job.cancelAndJoin() // 合并了对 cancel 以及 join 的调用
    println("org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main: Now I can quit.")
}

//协程的取消是 协作(cooperative) 的。一段协程代码必须协作才能被取消。
// 所有 kotlinx.coroutines 中的挂起函数都是 可被取消的 。
// 它们检查协程的取消， 并在取消时抛出 CancellationException。
// 然而，如果协程正在执行计算任务，并且没有检查取消的话，那么它是不能被取消的，就如如下示例代码所示：
private suspend fun CoroutineScope.cancelJobWithNoCheck() {
    val startTime = currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
            // 每秒打印两次消息  如果不进行检查的话，计算任务是不会被cancel的。
            if (currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L)    // 等待一段时间
    println("org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main: I'm tired of waiting!")
    job.cancelAndJoin()  // 取消一个作业并且等待它结束
    println("org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main: Now I can quit.")
}

private suspend fun cancelJobWithCheck() {
    coroutineScope {
        val startTime = currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            // isActive 是一个可以被使用在 CoroutineScope 中的扩展属性
            while (isActive) { // 可以被取消的计算循环
                // 每秒打印两次消息
                if (currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }
}

private suspend fun cancelJobWithFinally() {
    coroutineScope {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                println("job: I'm running finally")
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }
}

private suspend fun cancelWithContext() {
    coroutineScope {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                // 任何尝试在 finally 块中调用挂起函数的行为都会抛出 CancellationException
                // 因为这里持续运行的代码是可以被取消的。会导致后续输出语句无法执行。
//            println("job: I'm running finally")
//            delay(1000L)
//            println("job: And I've just delayed for 1 sec because I'm non-cancellable")

                // 当需要挂起一个被取消的协程，可使用 withContext(NonCancellable){}
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(2_000L)
                    println("job: And I've just delayed for 2 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }
}