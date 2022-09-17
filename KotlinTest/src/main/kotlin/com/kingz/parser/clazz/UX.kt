package com.kingz.parser.clazz

import com.kingz.kt.utils.HexUtil

abstract class UX {
    open lateinit var bytes:ByteArray

    open fun toHexString(): String {
        return HexUtil.hexBytesToString(bytes)
    }

    abstract fun toInt():Int
}