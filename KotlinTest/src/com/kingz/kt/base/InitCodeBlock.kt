package com.kingz.kt.base

/**
 * author: King.Z <br>
 * date:  2020/3/18 21:01 <br>
 * description:  <br>
 */
class InitCodeBlock{
   init {
        println("init()")
    }

    companion object {

        private var INSTANCE: InitCodeBlock? = null
            get() {
                if (field == null) {
                    field = InitCodeBlock()
                }
                return field
            }

        fun i(): InitCodeBlock {
            println("i()")
            return INSTANCE!!
        }
    }

}

object TestClient{

    @JvmStatic
    fun main(args: Array<String>) {
        InitCodeBlock.i()
        InitCodeBlock.i()
    }
}