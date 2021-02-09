package com.kingz.kt.coroutines.guide.contextAndDispatchers

import kotlinx.coroutines.*


/**
 * author: King.Z <br>
 * date:  2020/7/2 14:35 <br>
 * description: 协程作用域 <br>
 *
 *     将关于上下文，子协程以及Job的知识综合在一起。
 *
 *     假设我们的应用程序拥有一个具有生命周期的对象(是想说LiveData么？)，但这个对象并不是一个协程。
 *  举例来说，我们编写了一个 Android 应用程序，并在 Android 的 activity  context中启动了一组协程来使用异步操作拉取并更新数据以及执行动画等等。
 *  所有这些协程必须在这个 activity 销毁的时候取消以避免内存泄漏。当然，我们也可以手动操作Context与Job，以结合 activity 的生命周期与它的协程，
 *  但是 kotlinx.coroutines 提供了一个封装：CoroutineScope 的抽象。
 *
 *
 *  通过创建一个 CoroutineScope 实例来管理协程的生命周期，并使它与 activity 的生命周期相关联。
 *  CoroutineScope 可以通过 {@see CoroutineScope()} 创建或者通过 {@see MainScope()}  工厂函数。
 *  前者创建了一个通用作用域，而后者为使用 【Dispatchers.Main】 作为默认调度器的 UI 应用程序创建作用域：
 */

class Activity {
//    private val mainScope = MainScope()  // Ktx Android
    private val mainScope = CoroutineScope(Dispatchers.Default) // 为了进行测试，所以使用默认的Dispatcher

    fun doSomething() {
        // 启动了 10 个协程，且每个都工作了不同的时长
        repeat(10) { i ->
            mainScope.launch {
                delay((i + 1) * 200L) // 延迟 200 毫秒、400 毫秒、600 毫秒等等不同的时间
                println("Coroutine $i is done")
            }
        }
    }

    fun destroy() {
//        mainScope.cancel()  // IDEA自带的版本，没有canel。。。。
    }
    // 继续运行……
}

fun main() = runBlocking<Unit> {
    val activity = Activity()
    activity.doSomething() // run test function
    println("Launched coroutines")
    delay(500L) // delay for half a second
    println("Destroying activity!")
    activity.destroy() // cancels all coroutines
    delay(1000) // visually confirm that they don't work
}