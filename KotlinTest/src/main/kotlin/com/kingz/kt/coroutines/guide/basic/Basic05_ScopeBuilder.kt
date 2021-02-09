package com.kingz.kt.coroutines.guide.basic
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * author: King.Z <br>
 * date:  2020/7/1 10:37 <br>
 * description:
 *    除了由不同的构建器提供协程作用域之外，
 * 还可以使用 coroutineScope（注意是小写） 构建器声明自己的作用域。
 * 它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束。 <br>
 *   它与runBlocking看起来很类似，因为他们都会等待其协程体以及所有子协程结束。
 *   主要的区别在于：
 *     {@see runBlocking}方法会阻塞当前线程来等待, 而 coroutineScope 只是挂起，会释放底层线程用于其他用途。
 * 由于存在这点差异，runBlocking 是常规函数，而 coroutineScope 是挂起函数。
 */

fun main() = runBlocking {
    // 结构化并发，在指定作用域(runBlocking CoroutineScope)内启动协程
    launch {
        delay(200L)
        println("Task from runBlocking")
    }
    println("runBlocking CoroutineScope ")

    // 创建一个协程作用域(挂起的) 所有已启动子协程执行完毕之前不会结束
    coroutineScope {
        // 内嵌 launch
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        launch {
            delay(5000L)
            println("Task from nested launch-2")
        }

        delay(100L)
        println("Task from custom-coroutineScope") // 这一行会在内嵌 launch 之前输出
    }
//    注意，（当等待内嵌 launch 时）紧挨“Task from coroutine scope”消息之后，
//    就会执行并输出“Task from runBlocking”——尽管 coroutineScope 尚未结束

    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出 launch-2之后
}
/** 输出结果：
---> runBlocking CoroutineScope
---> Task from custom-coroutineScope
---> Task from runBlocking
---> Task from nested launch
---> Task from nested launch-2
---> Coroutine scope is over
*/
