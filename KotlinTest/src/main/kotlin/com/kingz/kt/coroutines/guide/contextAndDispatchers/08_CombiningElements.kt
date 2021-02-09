package com.kingz.kt.coroutines.guide.contextAndDispatchers
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * author: King.Z <br>
 * date:  2020/7/2 14:23 <br>
 * description: 组合上下文中的元素 <br>
 *  有时需要在协程上下文中定义多个元素。可以使用 + 操作符来实现。 因为重写了操作符。
 *  比如说，可以显式指定一个调度器来启动协程并且同时显式指定一个命名。
 *
 *  注意加 -Dkotlinx.coroutines.debug 来编译查看协程的标识;
 */

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Default + CoroutineName("test")) {
        println("I'm working in thread ${Thread.currentThread().name}")
    }
}