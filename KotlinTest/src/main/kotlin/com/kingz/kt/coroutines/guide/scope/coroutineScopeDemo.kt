package com.kingz.kt.coroutines.guide.scope

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
 * author: King.Z <br>
 * date:  2020/7/1 18:24 <br>
 * description:
 * CoroutineScope可以理解为协程的作用域，
 * 可以管理其域内的所有协程。一个CoroutineScope可以有许多的子scope。 <br>
 *     创建子scope的方式有许多种，常见的有：
 *     - 使用lauch, async 等builder创建一个新的子协程。
 *      协程(AbstractCoroutine)继承了 CoroutineScope，从父scope中继承了协程上下文(见下文CoroutineContext) 以及Job（见下文）
 *     - 使用coroutineScope Api创建新scope:
 *     public suspend fun <R> coroutineScope(block: suspend CoroutineScope.() -> R): R
 *     这个api主要用于方便地创建一个子域，并且管理域中的所有子协程。注意这个方法只有在所有 block中创建的子协程全部执行完毕后，才会退出。
 */

fun main() = runBlocking {
    coroutineScopeSequenceTest()
}

/**
 * coroutineScope的执行顺序。
 * print输出的结果顺序将会是 1， 2， 3， 4
 */
private suspend fun coroutineScopeSequence() {
    coroutineScope {
        delay(1000)
        println("1")
        launch {
            delay(3000)
            println("3")
        }
        println("2")
        return@coroutineScope
    }
    println("4")
}

/**
 * 测试局部返回带来的影响
 */
private suspend fun coroutineScopeSequenceTest() {
    coroutineScope { // block A
        println("1")
        if (false) {
            FunB(this)
            launch {
                println("2.5")
            }
//            return@coroutineScope
        }else{
            println("3")
        }
    }
    println("4")
}

suspend fun FunB( scope: CoroutineScope){ // block A
    thread {
//        sleep(500)
        println("1.5")
        scope.launch {
            println("2")
        }
    }
}
