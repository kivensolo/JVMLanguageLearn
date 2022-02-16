package com.kingz.kt.coroutines.guide.contextAndDispatchers

/**
 * author: King.Z <br>
 * date:  2020/7/2 10:55 <br>
 * description: 按照调试普通应用程序的方式（如:日志生命中打印线程名字），对协程来说是不怎么有用的，
 * 因为单独的线程名称不会给出很多协程上下文信息，所以kotlin包含了调试工具来让开发着更方便的调试。<br>
 */
import kotlinx.coroutines.*

// 如果用普通的打印线程名称的方式来调试协程,只会打印单独的线程名
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")


fun main() = runBlocking<Unit> {
//    printWithDebugFlag()
    printWithDebugName()
}

/**
 * 展示添加  "-Dkotlinx.coroutines.debug"  的JVM参数运行效果，
 * 打印时附带了当前正在其上执行的协程的标识符。
 */
private suspend fun CoroutineScope.printWithDebugFlag() {
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()}")
    log("runBlocking coroutine log.")
}

/**
 * 命名协程以用于调试
 * 自定义CoroutineScopeContext元素
 */
private suspend fun CoroutineScope.printWithDebugName() {
    log("Started org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.org.jetbrains.kotlinworkshop.introduction._8Delegation.com.kingz.kt.operators.main coroutine")
    // 运行两个后台值计算
    val jobA = async(CoroutineName("coroutine-jobA")) {
        log("JobA async befor delay.")
        delay(500)
        log("JobA async after delay.")
        252
    }
    val jobB = async(CoroutineName("coroutine-jobB")) {
        log("JobB async befor delay.")
        delay(1000)
        log("JobB async after delay.")
        6
    }
    log("The answer for jobA / jobB = ${jobA.await() / jobB.await()}")
}