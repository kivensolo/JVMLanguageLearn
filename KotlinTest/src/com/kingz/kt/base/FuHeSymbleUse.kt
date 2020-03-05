package com.kingz.kt.base

/**
 * author: King.Z <br>
 * date:  2020/3/5 14:36 <br>
 * description: 复合符号?:的使用方式  <br>
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