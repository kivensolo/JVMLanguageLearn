package com.kingz.kt.coroutines.guide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * 2. 连接阻塞与非阻塞的世界
 * 前面示例在同一段代码中混用了 非阻塞的 delay(……) 与 阻塞的 Thread.sleep(……)。
 * 这容易让人记混哪个是阻塞的、哪个是非阻塞的。
 * 所以,显式使用 runBlocking 协程构建器来进行阻塞：
 */
fun main() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L)
        println("Coroutines World!")
    }
    println("Hello,") // 主线程中的代码会立即执行

    println("------>")
//    runBlocking {     // 但是这个表达式阻塞了主线程
//        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
//    }
//    println("runBlocking end!") // 主线程中的代码会立即执行
}