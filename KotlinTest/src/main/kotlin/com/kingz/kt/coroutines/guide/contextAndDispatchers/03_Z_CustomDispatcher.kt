package com.kingz.kt.coroutines.guide.contextAndDispatchers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

/**
 * author: King.Z <br>
 * date:  2020/7/20 21:41 <br>
 * description:
 * 主要分析啥是 {#ContinuationInterceptor}
 * ContinuationInterceptor 继承了 CoroutineContext.Element，所以他也是一个 context:
 *
 *   interface ContinuationInterceptor : CoroutineContext.Element {
 *      fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T>
 *   }
 *  Continuation 代表着协程运行的某个中间运行状态.
 *  当需要继续执行协程时，Kotlin 会调用它的 【resumeWith】 方法。
 *  ContinuationInterceptor 的 interceptContinuation 可以返回一个新的 Continuation，
 *  在这个新的 Continuation 的 resumeWith 里面，可以让协程运行在任意的线程里。
 */

// 这里的 ContinuationInterceptor 其实是类 ContinuationInterceptor的 companion object，
// 并且这个伴生对象继承了 CoroutineContext.Key<ContinuationInterceptor>。
// 初看起来这种写法有点奇怪，但习惯了以后还是不得不承认这个是很优雅的设计（相当于一个协变类型的 map）
class DummyDispatcher : AbstractCoroutineContextElement(ContinuationInterceptor),
        ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return continuation // 返回原本的Continuation
    }
}

// -Dkotlinx.coroutines.debug
fun main() = runBlocking {
    val dispatcher = DummyDispatcher()
    log("which thread am I in?")
    val job = GlobalScope.launch(dispatcher) {
        log("launch before delay")  // DummyDispatcher 什么也没做，协程会继续在原来的线程中执行
        delay(100)                  // delay 内部使用了 DefaultExecutor 来实现延迟执行
        log("launch after delay")   // 此打印切换到了另一个线程
    }

    job.join()

//      [org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main @coroutine#1] which thread am I in?
//      [org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main @coroutine#1] launch before delay
//      [kotlinx.coroutines.DefaultExecutor] launch after delay

    customDispatcher()
}

/**
 * 自定义Dispatcher的测试
 */
private suspend fun customDispatcher() {
    val dispatcher = SingleThreadedDispatcher()
    log("which thread am I in?")
    val job = GlobalScope.launch(dispatcher) {
        log("launch before delay")
        delay(100)
        log("launch after delay")
    }

    job.join()
    dispatcher.close()

// [org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main @coroutine#1] which thread am I in?
// [SingleThreadedDispatcher] launch before delay
// [SingleThreadedDispatcher] launch after delay
}

/**
 * 构造一个真正的 ContinuationInterceptor.让协程运行在我们创建的线程池里。
 * 其实正常情况下，一般不会直接来实现 ContinuationInterceptor，
 * 而是实现它的子类 CoroutineDispatcher. (关注一下 interceptContinuation方法)
 * Q: 在哪里调用的 interceptContinuation？
 * A: 答案可以在 suspendCoroutine 的实现里找到：
 */
class SingleThreadedDispatcher : AbstractCoroutineContextElement(ContinuationInterceptor),
        ContinuationInterceptor {
    private val executorService = Executors.newSingleThreadExecutor {
        Thread(it, "SingleThreadedDispatcher")
    }

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return OurContinuation(continuation)
    }

    inner class OurContinuation<T>(private val origin: Continuation<T>) : Continuation<T>, Runnable {
        override val context: CoroutineContext = origin.context
        private var result: Result<T>? = null

        override fun resumeWith(result: Result<T>) {
//            executorService.submit {  // 每次调用 executorService.submit 我们都创建了一个新对象,对此进行优化
//                // origin.resumeWith 会执行协程中的代码。在这里调用的话，协程就
//                // 能运行在自定义的线程池中了
//                origin.resumeWith(result)
//            }
            this.result = result
            executorService.submit(this)
        }

        override fun run() {
            origin.resumeWith(result as Result<T>)
        }
    }

    fun close() {
        executorService.shutdown()
    }
}