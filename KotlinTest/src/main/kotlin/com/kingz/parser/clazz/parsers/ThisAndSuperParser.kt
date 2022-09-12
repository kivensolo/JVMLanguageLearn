package com.kingz.parser.clazz.parsers

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer

/**
 * this_class和super_class这两项，都占2字节
 * this_class存储的是常量池中某项常量的索引，
 * super_class要么为0，要么也是存储常量池中某项常量的索引。
 *
 * this_class和super_class指向的常量必须是一个CONSTANT_Class_info结构的常量。
 *
 * 只有Object类的super_class可以为0，接口的super_class指向常量池中
 * Object类的CONSTANT_Class_info常量。
 */
class ThisAndSuperParser:IBytesHandler {
    override fun order() = ParserOrder.ThisAndSuper
    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        val thisClassBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
        val superClassBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
        classFile.this_class = thisClassBytes
        classFile.super_class = superClassBytes
        println("this_class = #${HexUtil.readInt(thisClassBytes)}")
        println("super_class = #${HexUtil.readInt(superClassBytes)}")
    }
}