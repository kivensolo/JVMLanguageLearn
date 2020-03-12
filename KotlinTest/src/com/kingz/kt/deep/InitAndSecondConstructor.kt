package com.kingz.kt.deep

/**
 * author: King.Z <br>
 * date:  2020/3/6 10:39 <br>
 * description: init函数与construtor的学习 <br>
 */
class InitAndSecondConstructor(){
      /*属性*/
    private var gender: Boolean = true

    /*次构造方法*/
    constructor(name: String,gender: Boolean):this() {
        println("constructor")
    }

    /*初始化代码块*/
    init {
        println("Person init 2,gender:$gender")
    }

    /*初始化代码块*/
    init {
        println("Person init 1")
    }
}

object TestClient{
    @JvmStatic
    fun main(args: Array<String>) {
        InitAndSecondConstructor("zeke",true)
    }
}
