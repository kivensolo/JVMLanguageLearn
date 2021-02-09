package com.kingz.kt.base

/**
 * author: King.Z <br>
 * date:  2020/3/5 14:36 <br>
 * description: 复合符号的使用方式  <br>
 *     If not null 缩写 ---> ?.
 *     If not null and else 缩写  ---> ?:
 *     if not null  执⾏代码
 *          val value = ……
 *          value?.let {
 *               …… // 代码会执⾏到此处, 假如value不为null
 *          }
 *
 */
object FuHeSymbleUse {
    private val varString:String? = null

    private fun getVar():String?{
        return varString ?: "defaule"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("varString：" + getVar())
    }
}