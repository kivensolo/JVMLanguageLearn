package com.kingz.kt.lambda

/**

 * @author: King.Z
 * @since: 2021/3/5 20:02 <br>
 * @desc:
 *  理解 T.()->Unit 、 ()->Unit与 (T) -> Unit
 *
 *  结合Kotlin自带的作用域函数来理解：
 *   fun  T.apply(block: T.() -> Unit): T { block(); return this }
 *   fun  T.also(block: (T) -> Unit): T { block(this); return this }
 */
// 1. 自定义一个使用()->Unit的例子：
inline fun <T> T.doWithTry(block: () -> Unit) {
    try {
        block()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

//    使用演示 ---- 定义了一个Person类
class Person(val name:String){
    var age:Int = 0
    var sex:Int = 0
}

fun test() {
    val person= Person("张三")

    person.also {
        //没有指定参数名字,必须用it代指参数
        it.age = 20   //it不能省略
        it.sex = 0    //it不能省略
    }
    //或者
    person.also {personValue->
        //使用指定的参数名，同样personValue不能省略
        personValue.age = 20
        personValue.sex = 0
    }

    person.apply {
        //直接访问Person的属性
        this.age = 20  //this可以省略
        this.sex = 1   //this可以省略
    }

    person.doWithTry{
        //只能通过外部变量来访问Person
        person.age = 20
        person.sex = 1
    }

    person.doWithSelf {
        //T.()可以通过this访问对象参数和函数
        age = 20
        sex = 1
        10086
    }
}
/**
 * 上面的例子说明了以下几点：
 *
 * 1、T.()->Unit
 *    的函数体中可以直接使用T代表的对象，即用this代表对象。this的使用一般是一个类的成员函数中用来表示该类的实例对象本身，
 *    可以这样理解: T.()->Unit相当于是给类T定义了一个扩展函数，该函数没有形参，没有返回值，当然也可以增加参数与返回值，道理是一样的。
 *    正是因为T.()为T的扩展函数，所以可以在函数体里直接访问T对象的属性或者成员函数。
 *
 * 2、(T) -> Unit
 *    将T表示的对象作为实参通过函数参数传递进来，供函数体使用,
 *    可以使用默认的it
 *
 * 3、()->Unit
 *     与T表示的对象没有直接联系，只能通过外部T实例的变量来访问对象
 *
 *    (T) -> Unit与 ()->Unit只是一个普通的函数，一个带有参数，类型为T，另一个没有参数而已。
 */

//所以再定义一个 T.doWithSelf:

inline fun <T> T.doWithSelf(block: T.() -> Int){
    block()
}
