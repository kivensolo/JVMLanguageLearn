package com.kingz.kt.operators

/**
 * 集和调用 [index] 来 替代 get(index)，
 * 自己也可以来定义个类，来实现一下这个约定：
 */
fun main(){
    //这里就可以使用 [] 来替换 get来简化调用方法了
    val testBean = TestBean("wz",29)
    println(testBean[0])
}

/**
 * 重载get操作符示例
 */
data class TestBean(val name: String,val age: Int){
    //定义非常简单 使用operator重载运算符get方法
    operator fun get(index : Int): Any{
        return when(index) {
            0 -> name
            1 -> age
            else -> name
        }
    }
}