package com.kingz.kt.coroutines.exception

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException

/**
 * 探索协程中的异常处理，主要是在玩安卓项目中，发现直接launch一个携程，调用挂起函数的invoke，就可以tryCaych IO异常
 *  try {
 *      response = apiFun.invoke(getApiService(baseUrl))
 *      if (!response.httpIsSuccess) {
 *          throw ServerCodeBadException(response)
 *      }
 *  } catch (throwable: Throwable) {
 *      handleException(throwable, callback)
 *      return@launchMain
 *  }
 *
 * 如果是直接执行一个挂起的网络请求，则不行。奇了怪了
 *
 */
fun main(): Unit = runBlocking {
    launch {
        try {
            //直接执行挂起函数，无法捕获其中的IOException
//            doException()

            //用挂起函数包一层就可以了，为啥.....
            wrapperFun { doException() }
        } catch (e: IOException) {
            // Cannot catch IOException() here
            println("Try-catch Outer: $e")
        }
    }
    Unit
}

suspend fun wrapperFun(apiFun: suspend () -> Unit) {
    try {
        apiFun() //调用函数式参数
    } catch (throwable: Throwable) {
        println("Try-catch Inner throwable in coroutine.")
    }
}

/**
 * 模拟会出现异常的挂起函数
 */
private suspend fun doException() {
    delay(1_000)
    println("Throw exception.")
    throw IllegalArgumentException()
}