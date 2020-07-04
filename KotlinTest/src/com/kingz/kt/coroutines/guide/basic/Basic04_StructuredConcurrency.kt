package com.kingz.kt.coroutines.guide.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
/**
 * author: King.Z <br>
 * date:  2020/7/1 10:30 <br>
 * description: 结构化的并发 <br>
 *     当使用 GlobalScope.launch 时，会创建一个顶层协程。
 * 虽然它很轻量，但它运行时仍会消耗一些内存资源。
 * 如果忘记保持对新启动的协程的引用，它还会继续运行。
 * 必须手动保持对所有已启动协程的引用并 join 来管理他们，这很容易出错。
 *
 * 有一个更好的解决办法。可以在代码中使用结构化并发。
 * 可以在执行操作所在的【指定作用域内启动协程】， 而不是像通常使用线程（线程总是全局的）那样在 GlobalScope 中启动。
 *
 * 在之前的示例中，使用 runBlocking 协程构建器将 main 函数转换为协程。
 * 包括 runBlocking 在内的【每个协程构建器】都将 CoroutineScope 的实例添加到其代码块所在的作用域中。
 * 我们可以在这个作用域中启动协程而无需显式 join 之，因为外部协程（示例中的 runBlocking）直到在其作用域中启动的所有协程都执行完毕后才会结束。
 * 因此可以将前面的示例简化为：
 */

fun main() = runBlocking {
    // this: CoroutineScope

    launch { // 在 runBlocking 作用域中启动一个新协程
        delay(2000L)
        println("World!")
    }
    println("Hello,")
}