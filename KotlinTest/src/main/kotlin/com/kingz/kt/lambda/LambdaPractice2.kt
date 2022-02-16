package com.kingz.kt.base

/**

 * @author: King.Z
 * @since: 2020/3/8 20:02 <br>
 * @desc: 拖尾lambda表达式和括号省略情况下的代码测试
 */

interface ILambdaPractice {
    fun sayHello(excParms: (number: Int) -> Int)
    fun setIntentName(excParms: (inttent: Intent) -> Unit)
}

class LambdaPractice2 : ILambdaPractice {
    override fun setIntentName(excParms: (intent: Intent) -> Unit) {
        println("1. setIntentName 被调用~ 初始化intent;")
        val intent = Intent("test")
        println("2. intent.name = ${intent.name}, 调用函数参数代码修改数据(参数类型和返回值类型为Intent类型);")
        excParms(intent) //对象类型数据就会改变
        print("4. 处理完毕，输出baseNum的最终结果:${intent.name}")
    }

    override fun sayHello(excParms: (number: Int) -> Int) {
        println("1. sayHello 被调用~")
        val baseNum = 0
        println("2. 调用函数参数代码(参数类型和返回值类型为String类型)")
        excParms(baseNum)  //FIXME 基础类型数据不会改变值？？？
        println("4. 处理完毕，输出baseNum的最终结果:$baseNum")
    }
}

class Intent(var type: String) {
    var name: String = "default"
}

fun main() {
    println("外部调用sayHello方法，参数为自定义函数.")
    // 函数作为唯一参数,使用拖尾lambda表达式再加上把省略括号省略.
    LambdaPractice2().sayHello {
        println("3. 函数参数代码进行处理....")
        it.and(1)
    }
    println("============================")
    LambdaPractice2().setIntentName {
        println("3. 函数参数代码处理开始执行....")
        it.name = "set@zeke"
    }

// 输出打印结果
//    外部调用sayHello方法，参数为自定义函数.
//    1. sayHello 被调用~
//    2. 调用函数参数代码(参数类型和返回值类型为String类型)
//    3. 函数参数代码进行处理....
//    4. 处理完毕，输出baseNum的最终结果:0
//    ============================
//    1. setIntentName 被调用~ 初始化intent;
//    2. intent.name = default, 调用函数参数代码修改数据(参数类型和返回值类型为Intent类型);
//    3. 函数参数代码处理开始执行....
//    4. 处理完毕，输出baseNum的最终结果:set@zeke
}