package com.kingz.kt.coroutines.guide.contextAndDispatchers

/**
 * author: King.Z <br>
 * date:  2020/7/2 10:41 <br>
 * description:
 * 【非受限的调度器】
 * Dispatchers.Unconfined 协程调度器在调用它的线程启动了一个协程，
 * 但它仅仅只是运行到第一个挂起点。
 * 挂起后，它恢复线程中的协程，而这完全由被调用的挂起函数来决定。
 *
 * 非受限的调度器非常适用于执行不消耗 CPU 时间的任务，
 * 以及不更新局限于特定线程的任何共享数据（如UI）的协程。 <br>
 *
 *
 *     该调度器默认继承了外部的 CoroutineScope。 runBlocking 协程的默认调度器，
 * 特别是，当它被限制在了调用者线程时，继承自它将会有效地限制协程在该线程运行并且具有可预测的 FIFO 调度。
 */
import kotlinx.coroutines.*

fun main() = runBlocking {
    launchWithUnconfined()
    printWithDispatcher()
}

private fun CoroutineScope.launchWithUnconfined() {
    launch(Dispatchers.Unconfined) {
        // 非受限的 ----- 将和主线程一起工作
        // Unconfined 协程调度器在调用它的线程启动了一个协程，但它仅仅只是运行到第一个挂起点。 此处则为delay(500)
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        // 挂起后. 非受限的协程在默认的执行者线程中恢复执行。以为 delay 内部使用了 DefaultExecutor 来实现延迟执行
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }

   launch {
        // 父协程的上下文，主 runBlocking 协程
        println("org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }

/**
 * 输出结果：
 *  Unconfined      : I'm working in thread org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.main
 *  org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.main runBlocking: I'm working in thread org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.main
 *  Unconfined      : After delay in thread kotlinx.coroutines.DefaultExecutor
 *  org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.main runBlocking: After delay in thread org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.main
 *
 *
 *  思考一下，前后两个 Unconfined 的Dispatcher的运行线程不一样。
 */
}

private suspend fun printWithDispatcher() {
    val job1 = GlobalScope.launch {
        log("launch before delay")
        delay(100)
        log("launch after delay")
    }
    val job2 = GlobalScope.launch {
        log("launch2 before delay")
        delay(200)
        log("launch2 after delay")
    }

    job1.join()
    job2.join()

// 添加  "-Dkotlinx.coroutines.debug"  的输出打印:
//  [ForkJoinPool.commonPool-worker-1 @coroutine#2] launch before delay
//  [ForkJoinPool.commonPool-worker-3 @coroutine#3] launch2 before delay
//  [ForkJoinPool.commonPool-worker-3 @coroutine#2] launch after delay
//  [ForkJoinPool.commonPool-worker-3 @coroutine#3] launch2 after delay
//有两个要点：
//    1.从线程名推断，两个协程很可能运行在某个线程池中
//    2.第1个协程先运行在 worker-1，然后又运行在 worker-3   (和前面的例子一样)
//
//  关于第一点,launch的文档有这句话:
//    If the context does not have any dispatcher nor any other ContinuationInterceptor,
//    then Dispatchers.Default is used.
//    正是Distpacher决定了协程运行在哪个线程里。所谓的 Dispatcher，中文可以叫它分发器，
//    是用来将协程分发到特定线程去执行的。它的接口是 ContinuationInterceptor。


//  关于第2点,第1个协程先后运行的线程名不一样，这就提醒着我们，很多时候不能假设协程会运行在同一个线程里，
//    它唯一保证的是，协程中的代码会串行执行。由于协程是串行执行的，即使前后不是在同一个线程，我们也能安全地对局部变量进行读写：
}