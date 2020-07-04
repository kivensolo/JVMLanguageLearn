package com.kingz.kt.buildin

/**

 * @author: King.Z
 * @since: 2020/3/25 21:17 <br>
 * @desc: 内联函数的使用
 */
object InLineTest {
    private var mp: People? = null

    @JvmStatic
    fun main(args: Array<String>) {
        letFunction()
        alsoFunction()
        withFunction()
        runFunction()
        applyFunction()
    }

    /**
     * let 函数使用
     */
    private fun letFunction() {
        println("---- let_Function:")
        mp = People()
        mp?.let {
            //函数体内使用it替代People对象去访问其公有的属性和方法
            it.name = "zeke"
            it.age = 26
        }

        mp = People().let {
            it.name = "zeke"
            it.age = 26
            it  // 这种写法需要返回个it
        }
        println("age=${mp?.age}")
    }

    /**
     * 和let很像  就是返回值不同, alse返回对象自身
     * 适用场景:
     *  适用于let函数的任何场景。
     *  一般可用于多个扩展函数链式调用
     */
    private fun alsoFunction() {
        println("---- also_Function:")
        val result = "testLet".also {
            println(it.length)
            1000
        }
        println(result)
    }

    /**
     * 这个标准函数的作用是Lambda中的代码会持有对象的上下文，其最后一行代码为返回值。
     * with 函数使用: <p/>
     *  with(object){
     *    // doSomething
     *  }
     *
     * 适用于调用同一个类的多个方法时，
     * 可以省去类名重复，直接调用类的方法即可，
     * 经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上。
     */
    private fun withFunction() {
        println("---- with_Function:")
//        val kt = People("Kotlin", 1, 10086)
//        val result = with(kt) {
//            println("my name is $name, I am $age years old, my phone number is $phoneNum")
//            1000
//        }
        val result = "Kotlin".times(2)
        println("result: $result")
    }
    // with效果对比
    operator fun String.times(n: Int): String {
//        val sb = StringBuilder()
//        repeat(n) {
//            sb.append(this)
//        }
//        return sb.toString()
        return with(StringBuilder()) {
            repeat(n) {
                append(this@times)
            }
            toString()
        }
    }


    /**
     * run是let和with两个函数函数结合体
     * 适用于let,with函数任何场景。
     * 返回值为最后一行代码
     */
    private fun runFunction() {
        println("---- run_Function:")
        val kt = People("run", 2, 10000)
        val result = kt.run  {
            println("my name is $name, I am $age years old, my phone number is $phoneNum")
            "end"
//            return@run "end"
        }
        println("result: $result")
    }

    /**
     * apply函数 和 run函数很像，也是let和with两个函数函数结合体
     * 区别在于返回值为对象本身
     */
    private fun applyFunction() {
        println("---- apply_Function:")
        val kt = People("apply", 3, 10003)
        val result = kt.apply {
            println("my name is $name, I am $age years old, my phone number is $phoneNum")
            "end"
        }
        println("result: $result")
    }


}

class People(var name: String = "",
             var age: Int = 0,
             var phoneNum: Int = 0)