package com.kingz.kt.clazz

/**
 * Kotlin嵌套类和内部类的区别：
 * 1. 访问外部类成员变量时有区别；
 * 2. 创建嵌套类和内部类的方式有区别；
 *
 */
class Parent {
    val name = "Name of out class."


    class NestedChild { // Default is nested classes
        fun getName():String{
            // can't get parent's name attr.
            return "B"
        }
    }

    inner class InnerChild{
        var name = "Name of inner class."
        fun  getSelfName():String{
            return name
        }

        fun  getOutName():String{
            return this@Parent.name
        }
    }
}

fun main() {
    println(Parent.NestedChild().getName())
    println(Parent().InnerChild().getSelfName())
    println(Parent().InnerChild().getOutName())
}

