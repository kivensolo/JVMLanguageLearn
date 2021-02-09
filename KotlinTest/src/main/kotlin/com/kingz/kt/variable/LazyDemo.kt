package com.kingz.kt.variable


class DemoClass{
    val content = "Demo class content."
}

object LazyDemo {
    private val demoClass by lazy {
        println("Init demoClass.")
        DemoClass()  // 最后一行为返回值
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // 第一次调用，进行Lazy函数内部操作执行
        println("DemoClass toString1：" + demoClass.toString())
        println("Content：" + demoClass.content)
        // 直接获取到demoClass对象
        println("DemoClass toString2：" + demoClass.toString())
    }
}