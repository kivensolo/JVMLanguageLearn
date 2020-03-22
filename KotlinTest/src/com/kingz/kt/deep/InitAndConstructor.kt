package com.kingz.kt.deep

/**
 * author: King.Z <br>
 * date:  2020/3/6 10:39 <br>
 * description: init函数与construtor的学习 <br>
 *  【主构造函数】:
 *     1.主构造函数不能包含任何的代码(只能是parms)，初始化的代码可以放到以 init <br>
 *       关键字作为前缀的初始化块(initializer blocks)中  <br>
 *       主构造的参数可以在初始化代码块和类体中使⽤.  <br>
 *
 *       注意：在 JVM 上，如果主构造函数的所有的参数都有默认值，编译器会⽣成
 *       ⼀个额外的⽆参构造函数，它将使⽤默认值。
 *
 *     2. 如果构造函数有注解或可⻅性修饰符(private之类)，constructor 关键字是必需的,并且这些修饰符在它前⾯.
 *        class Customer public @Inject constructor(name: String) { /*……*/ }
 *
 */
class InitAndConstructor private constructor(name : String = "default"){  // 主构造函数是类头的⼀部分, 主构造函数的参数可以为空

    /** 属性  最终的赋值操作，会融入到主构造函数中*/
    private var gender: Boolean = true

    constructor(name: String,gender: Boolean):this(name) {
        this.gender = gender
        println("constructor")
    }

    init {
        println("Init block First !! gender:$gender")
    }

    init {
        println("Init block Second !!")
    }

    companion object {
        init {
            println("Init block in companion object!!")
        }
    }
}

object TestClient{
    @JvmStatic
    fun main(args: Array<String>) {
        InitAndConstructor("zeke",true)
    }
}
