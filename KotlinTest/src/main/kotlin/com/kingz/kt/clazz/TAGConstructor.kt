package com.kingz.kt.clazz

abstract class Base{
    // javaClass: 得到当前对象运行时的javaClass对象
    val TAG = javaClass.simpleName

    //Base::class 是KClass的实例, .java是得到Java的Class实例
    val TAG_2 = Base::class.java.simpleName

    abstract fun show()
}

class AClass : Base(){
    override fun show() {
        println("TAG is $TAG")
        println("TAG_2 is $TAG_2")
    }
}
class BClass : Base(){
     override fun show() {
        println("TAG is $TAG")
        println("TAG_2 is $TAG_2")
    }
}

object TagClient{
    @JvmStatic
    fun main(args: Array<String>) {
        println(BClass().show())
        println(AClass().show())
    }

}