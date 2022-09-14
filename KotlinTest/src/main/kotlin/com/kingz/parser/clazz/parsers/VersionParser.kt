package com.kingz.parser.clazz.parsers

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer

class VersionParser: IBytesHandler{
    override fun order()= ParserOrder.Version

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        val minorBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
        classFile.minor_version = minorBytes
        val majorBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
        classFile.major_version = majorBytes

        val minor = HexUtil.readInt(minorBytes)
        val major = HexUtil.readInt(majorBytes)
        val desc = (major - 45).toFloat()
        val jdkMajorVersion = 1.1f + (desc/10)
        println("  minor version: $minor")
        println("  major version: $major [$jdkMajorVersion]")
    }
}