@file:Suppress("MemberVisibilityCanBePrivate")

package com.kingz.kt.lambda

import kotlinx.coroutines.runBlocking
/**
 * @author: King.Z
 * @since: 2021/2/15 16:49 <br>
 * @desc: Function Style 复习
 */
fun main() = runBlocking{
    wrapperFun{ doSomething() }

    //下面写法等同于上面
    val function0 = object: Function0<Unit> {
        override fun invoke() {
            println("Invoked by function method.")
        }
    }
    wrapperFun(function0)



    hightFunTest("Invoke test"){
        println("高级回调函数被调用")
        onSuccess {
            println("Invoked onSuccess callback.")
            println("返回数据是：$it")
        }
     }
}

// <editor-fold defaultstate="collapsed" desc="Invoke 特性">
fun doSomething() {
    println("Invoked by function method.")
}

fun wrapperFun(block: () -> Unit) {
    /**
     * 此处的block是一个函数类型的类对象,对其直接使用括号进行函数调用，是因为
     * 其为FunctionN接口的类，内部有invoke重载方法。
     * 而有invoke重载方法，就可以这样玩，就是invoke约定规则。
     */
    block() // 等同于block.invoke()
}
// </editor-fold>


// <editor-fold defaultstate="collapsed" desc="高阶函数应用测试">

class RequestCallback<DATA>(internal var onSuccess: ((DATA?) -> Unit)? = null){
    fun onSuccess(block: (data: DATA?) -> Unit) {
        println("设置onsuccess 的回调函数")
        this.onSuccess = block
    }
}

fun <DATA> hightFunTest(data: DATA, callbackFun:(RequestCallback<DATA>.()-> Unit)? = null){
    val callback = if(callbackFun == null){
        null
    }else{
        RequestCallback<DATA>().apply {
            callbackFun.invoke(this)
        }
    }
    callback?.onSuccess?.invoke(data)
}
// </editor-fold>