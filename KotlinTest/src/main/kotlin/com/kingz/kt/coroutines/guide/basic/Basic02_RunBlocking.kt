package com.kingz.kt.coroutines.guide.basic

import com.kingz.kt.utils.printlnWithTime
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * 2. 连接阻塞与非阻塞的世界
 * 前面示例在同一段代码中混用了 非阻塞的 delay(……) 与 阻塞的 Thread.sleep(……)。
 * 这容易让人记混哪个是阻塞的、哪个是非阻塞的。
 * 所以,显式使用 runBlocking 协程构建器来进行阻塞：
 */
fun main() {
    // 在后台启动一个新的协程并继续
    GlobalScope.launch {
        delay(1000L)
        printlnWithTime("Coroutines World!  Coroutines End.")
    }
    printlnWithTime("Hello,") // 主线程中的代码会立即执行

    printlnWithTime("------ runBlocking()  org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main thread blocked 2s--->")
    runBlocking {
        // 但是这个表达式阻塞了主线程
        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
    }
    printlnWithTime("runBlocking end!")
}

// 上面这个示例可以用更惯用的方式重写，使用 runBlocking 来包装 org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main 函数的执行
fun main2() = runBlocking<Unit> {// 开始执行主协程
    GlobalScope.launch {
        // 在后台启动一个新的协程并继续
        delay(1000L)
        printlnWithTime("Coroutines World!  Coroutines End.")
    }
    printlnWithTime("Hello")
    delay(2000L)      // 延迟 2 秒来保证 JVM 存活
}

// 这也是为挂起函数编写单元测试的一种方式：
class MyTest {
//    @Test
    fun testMySuspendingFunction() = runBlocking<Unit> {
        // 这里我们可以使用任何喜欢的断言风格来使用挂起函数
    }
}