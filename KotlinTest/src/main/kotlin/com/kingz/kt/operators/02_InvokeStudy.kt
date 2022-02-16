package com.kingz.kt.operators

/**
 * Invoke约定，让对象像函数一样调用方法，比如TestInvoke所演示的。
 *
 */
fun main(){

    val testInvoke = TestInvoke("wz",29)
    println(testInvoke()) // 等同于testInvoke.invoke()
}

/**
 * Invoke约定，让对象像函数一样调用方法
 */
data class TestInvoke(val name: String, val age: Int){

    //重载invoke方法
    operator fun invoke() : String{
        return innerFun()
    }

    private fun innerFun():String{
        return "$name - $age"
    }
}

data class InvokeWithLambda(){

}