package com.kingz.parser.clazz.parsers

import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.U2
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer

class VersionParser: IBytesHandler{
    override fun order()= ParserOrder.Version

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {

        classFile.minor_version = U2(codeBuf.get(),codeBuf.get())
        classFile.major_version = U2(codeBuf.get(),codeBuf.get())

        val minor = classFile.minor_version!!.toInt()
        val major = classFile.major_version!!.toInt()
        val desc = (major - 45).toFloat()
        val jdkMajorVersion = 1.1f + (desc/10)
        println("  minor version: $minor")
        println("  major version: $major [$jdkMajorVersion]")
    }
}