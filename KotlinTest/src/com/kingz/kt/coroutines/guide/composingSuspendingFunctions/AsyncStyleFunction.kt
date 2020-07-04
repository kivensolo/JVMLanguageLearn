package com.kingz.kt.coroutines.guide.composingSuspendingFunctions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * author: King.Z <br>
 * date:  2020/7/1 17:41 <br>
 * description: 可以定义异步风格的函数来 异步 的调用任务函数，
 * 并使用 async 协程建造器并带有一个显式的 GlobalScope 引用。
 * 一般给这样的函数的名称中加上“……Async”后缀来突出表明：
 * 事实上，它们只做异步计算并且需要使用延期的值来获得结果。 <br>
 *
 */


// 注意，在这个示例中我们在 `main` 函数的右边没有加上 `runBlocking`
//fun main() {
//    useBadStyle()
//
//}

private fun useBadStyle() {
    val time = measureTimeMillis {
        // 可以在协程外面启动异步执行
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()

        // 但是等待结果必须调用其它的挂起或者阻塞
        // 当我们等待结果的时候，这里我们使用 `runBlocking { …… }` 来阻塞主线程
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}


fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}

/**
 * 注意，这些 xxxAsync 函数不是 挂起 函数。它们可以在任何地方使用
 * 但是，它们总是在调用它们的代码中意味着异步（这里的意思是 并发 ）执行。
 */
// somethingUsefulOneAsync 函数的返回值类型是 Deferred<Int>
fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// somethingUsefulTwoAsync 函数的返回值类型是 Deferred<Int>
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

/**
* 上面这种带有异步函数的编程风格仅供参考，因为这在其它编程语言中是一种受欢迎的风格。
* 在 Kotlin 的协程中使用这种风格是【强烈不推荐】的， 原因细品:
* http://www.kotlincn.net/docs/reference/coroutines/composing-suspending-functions.html
*/


/**
 * 将函数写在同一作用域内，
 * 这种情况下，如果在 concurrentSum 函数内部发生了错误，
 * 并且它抛出了一个异常， 所有在作用域中启动的协程都会被取消。
 */
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}