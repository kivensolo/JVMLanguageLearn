package com.kingz.kt.coroutines.guide.cancellationandtimeouts

/**
 * author: King.Z <br>
 * date:  2020/7/1 16:35 <br>
 * description: <br>
 *   如果你需要做一些各类使用超时的特别的额外操作，可以使用类似 withTimeout 的 withTimeoutOrNull 函数，
 * 并把这些会超时的代码包装在 try {...} catch (e: TimeoutCancellationException) {...} 代码块中，
 * 而 withTimeoutOrNull 通过返回 null 来进行超时操作，从而替代抛出一个异常.
 */
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    timeOutWithNUll()
    timeOutWithExceprion()
}

private suspend fun timeOutWithExceprion() {
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
}

private suspend fun timeOutWithNUll() {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // 在它运行得到结果之前取消它
    }
    println("Result is $result")
}