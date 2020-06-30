package com.kingz.kt.coroutines.guide

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 1. 第一个协程程序
fun main() {
    GlobalScope.launch { // 在后台启动⼀个新的协程并继续
        delay(1000L) // ⾮阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
    }
//    thread {
//        Thread.sleep(1000L)
////        delay(1000L)  // 不能使用挂起函数，因为挂起函数只能在coroutine协程或其他挂起函数中被调用
//        println("World!")
//    }
//    println("Hello,") // 协程已在等待时主线程还在继续
//    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
}
