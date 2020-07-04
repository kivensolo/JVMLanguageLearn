package com.kingz.kt.coroutines.guide.basic

/**
 * author: King.Z <br>
 * date:  2020/7/1 10:20 <br>
 * description: 延迟一段时间来等待另一个协程运行并不是一个好的选择，
 * 大多数情况下，子协程后台任务的耗时时长是主协程不知道的。
 * 这个示例展示了显式（以非阻塞方式）等待所启动的后台 Job 执行结束： <br>
 */
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
        delay(2000L)
        println("World!")
    }
    println("Hello,")
    job.join() // 等待子协同程序完成
    println("job.join() 是否会阻塞主线程? 会")
}