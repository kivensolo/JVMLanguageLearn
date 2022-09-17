package com.kingz.parser.clazz

open class U2(b1: Byte, b2: Byte) : UX() {

    init {
        bytes = byteArrayOf(b1, b2)
    }

    override fun toInt():Int {
        return (bytes[0].toInt() and 0xff) shl 8 or  (bytes[1].toInt() and 0xff)
    }
}