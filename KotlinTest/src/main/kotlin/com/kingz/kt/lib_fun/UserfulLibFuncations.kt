package com.kingz.kt.lib_fun

/**
 * 介绍kotlin标准库中有用的函数：
 * run\let\takeIf\takeUnless\repeat
 */
object UserfulLibFunctions {

    // Run function: inline fun <T, R> T.run(block: T.() -> R): R
    private val foo = run {
        println("Calculation foo ...")
        "foo"
    }
    fun LetFun() {
        //inline fun <T, R> T.let(block: (T) -> R): R
        foo.let {
        }
    }
    // run和let以及apply等在日常开发中是比较常用的


    fun TakeIf(){
        val number = 42
        // 若满足lambda表达式中的指定条件，则返回本身的值，否则返回null
        number.takeIf { it > 10 } // 42

        val other = 2
        other.takeIf { n -> n > 10 } //null
    }
    fun TakeUnless(){
        val number = 42
        // 若不满足lambda表达式中的指定条件，则返回本身的值，否则返回null
        number.takeUnless { it > 10 } // null

        val other = 2
        other.takeUnless { it > 10 } //2
    }

    /**
     * 重复执行，内部就是用的for循环执行labmda表达式
     */
    fun Repeat(){
        // inline fun repeat(times: Int, action: (Int) -> Unit)
        repeat(10){ index ->
            println("Welcome +1 :$index")
        }
    }
}

object InlineTest{

    // 过滤列表中目标类型的数据
    inline fun <reified T> List<*>.filterIsInstance():List<T>{
        val targets = mutableListOf<T>()
        for (element in this){
            if(element is T){
                targets.add(element)
            }
        }
        return targets
    }
    val anys:List<Any> = listOf(1, "one", 2.0)
    val strings:List<String> = anys.filterIsInstance()

   @JvmStatic
    fun main(args: Array<String>) {
       println(strings)
    }
}