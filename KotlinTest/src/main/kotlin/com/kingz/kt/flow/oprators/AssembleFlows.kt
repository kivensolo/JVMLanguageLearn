package com.kingz.kt.flow.oprators
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


/**
 * zip和combine操作符的核心区别在于元素组合的时机和方式
 *
 *  zip 操作符：
 *      按 "位置配对"，只有当两个流都产生了第n个元素时才会组合，且每个元素仅参与一次组合。
 *  combine 操作符：
 *      按 "最新值配对"，任何一个流产生新元素时，就用两个流当前的最新元素组合。
 *  */
suspend fun assembleDoubleFlowWithDiffDelay(useCombine:Boolean) {
    val nums = (1..3).asFlow().onEach {
        delay(300) // 使用onEach来延迟每个元素，并使发出样本流的代码更具声明性
    }  // numbers 1..3
    val strs = flowOf("one", "two", "three").onEach {
        delay(400) // strings  emit every 400
    }

    val startTime = System.currentTimeMillis()
    val operateFlow:Flow<String> = if(useCombine){
        nums.combine(strs) { num, str -> "$num -> $str" } // compose a single string with "combine"
    }else{
        nums.zip(strs) { num, str -> "$num -> $str" } // compose a single string with "zip"
    }
    operateFlow.collect { value -> // collect and print
        println("$value at ${System.currentTimeMillis() - startTime} ms from start")
    }
}

fun main() = runBlocking<Unit> {
//sampleStart
    //Zip和combine方式组合流
    assembleDoubleFlowWithDiffDelay(true)
//sampleEnd
}

