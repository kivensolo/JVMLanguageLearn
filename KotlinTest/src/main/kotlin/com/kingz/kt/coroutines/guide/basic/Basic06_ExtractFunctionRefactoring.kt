package com.kingz.kt.coroutines.guide.basic

import com.kingz.kt.utils.printlnWithTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * author: King.Z <br>
 * date:  2020/7/1 11:25 <br>
 * description:  <br>
 *    普通的函数提取可以参见FirstCoroutine，
 * 但是如果提取出的函数包含一个在当前作用域中调用的协程构建器的话，该怎么办？
 * 在这种情况下，所提取函数上只有 suspend 修饰符是不够的。
 *   为 CoroutineScope 写一个 doWorld 扩展方法是其中一种解决方案，
 * 但这可能并非总是适用，因为它并没有使 API 更加清晰。
 *
 *      惯用的解决方案是要么显式将 CoroutineScope 作为包含该函数的类的一个字段，
 * 要么当外部类实现了 CoroutineScope 时隐式取得。
 *
 * 作为最后的手段，可以使用 CoroutineScope(coroutineContext)，不过这种方法结构上不安全，
 * 因为你不能再控制该方法执行的作用域。只有私有 API 才能使用这个构建器。
 */
fun main() = runBlocking<Unit> {// 开始执行主协程
    doTest()
}

private suspend fun CoroutineScope.doTest() {
    launch {
        // 在后台启动一个新的协程并继续
        delay(1000L)
        printlnWithTime("Coroutines World!  Coroutines End.")
    }
    printlnWithTime("Hello")
    delay(2000L)      // 延迟 2 秒来保证 JVM 存活
}