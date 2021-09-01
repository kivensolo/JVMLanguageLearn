package com.kingz.kt.clazz

/**
 * Kotlin嵌套类和内部类的区别：
 * 1. 访问外部类成员变量时有区别；
 *      嵌套类(默认的)不能直接访问外部类变量；
 *      内部类因为持有外部类引用，所以可以直接访问外部类变量，
 *      若外部类和内部类自身有同名变量，访问时可通过this@Outer.filed来访问外部类变量。
 *
 * 2. 使用时，创建嵌套类和内部类实例的方式有区别；
 *      嵌套类:Outer.Nested()
 *      内部类：Outer().Inner()
 *
 * 3. 伴生对象
 *
 */
class Parent {
    val name = "Name of out class."

    fun launchIO(){}

    class NestedChild { // Default is nested classes
        fun getName():String{
            // can't get parent's name attr.
            return "B"
        }

        fun invokeParentMethod(){
            Parent().launchIO()
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

    //编译后是static & final 的,无法访问外部类函数
    companion object Demo{
//        launchIO()  can't invoke
    }
}

fun main() {
    println(Parent.NestedChild().getName())
    println(Parent().InnerChild().getSelfName())
    println(Parent().InnerChild().getOutName())
}

