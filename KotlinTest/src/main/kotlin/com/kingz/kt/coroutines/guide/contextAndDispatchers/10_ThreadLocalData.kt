package com.kingz.kt.coroutines.guide.contextAndDispatchers

import kotlinx.coroutines.*

/**
 * author: King.Z <br>
 * date:  2020/7/3 17:30 <br>
 * description: 有时，能够将一些线程局部数据传递到协程与协程之间是很方便的。
 * 然而，由于它们不受任何特定线程的约束，如果手动完成，可能会导致出现样板代码。
 *
 * ThreadLocal， asContextElement 扩展函数在这里会充当救兵。
 * 它创建了额外的上下文元素，且保留给定 ThreadLocal 的值，并在每次协程切换其上下文时恢复它。 <br>
 */

val threadLocal = ThreadLocal<String?>() // 声明线程局部变量

fun main() = runBlocking<Unit> {
    threadLocal.set("org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main")
    println("Pre-org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")

    // 在后台线程池中启动了一个新的协程,它工作在线程池中的不同线程中，但它仍然具有线程局部变量的值
    // 指定使用 threadLocal.asContextElement(value = "launch")， 无论协程执行在什么线程中都是没有问题的。
    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        yield()
        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }

    job.join()
    println("Post-org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
}