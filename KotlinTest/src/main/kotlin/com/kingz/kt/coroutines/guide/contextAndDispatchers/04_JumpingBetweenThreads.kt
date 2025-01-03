package com.kingz.kt.coroutines.guide.contextAndDispatchers

/**
 * author: King.Z <br>
 * date:  2020/7/2 11:12 <br>
 * description:
 * 主要展示协程在线程中的切换方式。
 *
 * 使用 withContext 函数来改变协程的上下文，但仍然驻留在相同的协程中 <br>
 */
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            //runBlocking  显式指定了一个上下文
            runBlocking(ctx1) {
                log("Started in ctx1")
                // withContext 函数来改变协程的上下文. 但仍然驻留在相同的协程中
                withContext(ctx2) {
                    log("Working in ctx2")
                }
                log("Back to ctx1")
            }
        }
    }

    /**
     * use 函数
     * 当不再需要某个在 newSingleThreadContext 中创建的线程的时候， 可使用use函数来释放该线程。
     */
}