package com.kingz.parser.clazz

class U4(b1: Byte, b2: Byte, b3: Byte, b4: Byte) : UX() {

    init {
        bytes = byteArrayOf(b1, b2, b3, b4)
    }

    override fun toInt():Int {
        val v1 = (bytes[0].toInt() and 0xff) shl 24
        val v2 = (bytes[1].toInt() and 0xff) shl 16
        val v3 = (bytes[2].toInt() and 0xff) shl 8
        val v4 = bytes[3].toInt() and 0xff
        return v1 or v2 or v3 or v4
    }
}