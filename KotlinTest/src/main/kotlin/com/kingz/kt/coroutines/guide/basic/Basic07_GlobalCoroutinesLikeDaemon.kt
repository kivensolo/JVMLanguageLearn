package com.kingz.kt.coroutines.guide.basic
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * author: King.Z <br>
 * date:  2020/7/1 11:36 <br>
 * description: GlobalScope中启动的活动协程并不会使进程保活。
 * 它们就像守护线程。 当用户线程执行完毕时，也就没有存在的意义了。 <br>
 */
fun main() = runBlocking {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // 在延迟后退出
}