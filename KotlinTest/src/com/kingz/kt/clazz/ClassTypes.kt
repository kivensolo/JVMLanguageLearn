package com.kingz.kt.clazz

/**
 * Kotlin嵌套类和内部类的区别
 */
class ClassA {
    val name = "Name of out class."
    class ClassB {
        fun getName():String{
            // can't get A name attr.
            return "B"
        }
    }
    inner class ClassC{
        var name = "Name of inner class."
        fun  getSelfName():String{
            return name
        }
        fun  getOutName():String{
            return this@ClassA.name
        }
    }
}

fun main() {
    println(ClassA.ClassB().getName())
    println(ClassA().ClassC().getSelfName())
    println(ClassA().ClassC().getOutName())
}

// 默认为嵌套类