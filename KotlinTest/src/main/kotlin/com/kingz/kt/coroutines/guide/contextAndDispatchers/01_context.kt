package com.kingz.kt.coroutines.guide.contextAndDispatchers

/**
 * author: King.Z <br>
 * date:  2020/7/2 10:23 <br>
 * description:
 *      协程上下文包含一个 【协程调度器】：
 * 它确定了哪些线程或与线程相对应的协程执行。
 * 协程调度器可以将协程限制在一个特定的线程执行，或将它分派到一个线程池，亦或是让它不受限地运行。
 *
 *      所有的协程构建器诸如 launch 和 async 接收一个可选的 CoroutineContext 参数，
 * 它可以被用来显式的为一个新协程或其它上下文元素指定一个调度器。 <br>
 */
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch {
        // 当调用 launch { …… } 时不传参数，它从启动了它的 CoroutineScope 中承袭了上下文（以及调度器）
        // 此案例中，运行在父协程的上下文中，即 runBlocking 主协程
        println("org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        // 不受限的——将工作在主线程中
        // 是一个特殊的调度器且似乎也运行在 org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.main 线程中，但实际上， 它是一种不同的机制
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) {
        // 将会获取默认调度器, 默认调度器使用共享的后台线程池。
        // 所以 launch(Dispatchers.Default) { …… } 与 GlobalScope.launch { …… } 使用相同的调度器。
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("MyOwnThread")) {
        // newSingleThreadContext为协程的运行启动了一个线程
        // 将使它获得一个新的线程
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }

    // 打印得输出也许顺序会有所不同
}