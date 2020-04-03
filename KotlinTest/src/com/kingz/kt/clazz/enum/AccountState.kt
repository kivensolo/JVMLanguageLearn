package com.kingz.kt.clazz.enum

import java.util.*


/**
 * author: King.Z <br>
 * date:  2020/3/29 21:32 <br>
 * description: kotlin枚举用法,和java相同  <br>
 *
 *     values()函数，得到当前枚举类的枚举数组(按顺序) 。
 *     valueOf(value: String)，将常量的名字转变成常量本身。如果指定的名称与类中定义的任何枚举常量均不匹配，
 *     valueOf() ⽅法将抛出 IllegalArgumentException 异常。
 */

// 演示枚举初始化用法
enum class AccountState(private val type: String) {
    None("N/A"),
    Normal("正常"),
    Erasure("销户");

    // private val type: String = type
    companion object {
        //用于储存type类型和枚举类型的映射关系
        private val stringToEnum: MutableMap<String, AccountState> = HashMap()

        fun fromType(type: String?): AccountState? {
            return stringToEnum[type]
        }

        init {
            println("init{ }")
            for (value in values()) {
                println("init-for: value=$value")
                stringToEnum[value.toString()] = value
            }
        }
    }

    override fun toString(): String {
        return type
    }
}

/**
 * 实现抽象方法  就像TimeUnit一样
 */
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },
    TALKING {
        override fun signal() = WAITING
    };
    abstract fun signal(): ProtocolState
}

fun main(){
    val accountState = AccountState.Normal
    val fromType = AccountState.fromType("正常")
    println("accountState=$accountState ; fromType=${fromType?.name}")
    // accountState=正常 ; fromType=Normal
    println("accountState=$accountState ; fromType=${fromType}")
    // accountState=正常 ; fromType=正常

    val signal = ProtocolState.WAITING.signal()
    println("signal=$signal")

}