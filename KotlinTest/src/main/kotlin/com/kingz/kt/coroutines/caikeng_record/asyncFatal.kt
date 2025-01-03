package com.kingz.kt.coroutines.caikeng_record

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * https://e.gitee.com/starcor-com/projects/365508/tasks/list?issue=I52A9H
 */

fun main() {
    GlobalScope.launch{
        var time = System.currentTimeMillis()
        val aDeferred = async {
            delay(2000)
            "Hello"
        }.await()

        val bDeferred = async {
            "World"
        }.await()
        val cost = System.currentTimeMillis() - time
        println("a+b=${aDeferred+bDeferred}  Cost:$cost")
    }
}