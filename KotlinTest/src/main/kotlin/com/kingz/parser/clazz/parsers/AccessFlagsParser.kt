package com.kingz.parser.clazz.parsers

import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.U2
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.utils.AccessFlagUtils
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer

/**
 * 访问标识符号解析器
 * 解析class文件是类还是接口，是否定义为public的,是否是abstract,是否被 final修饰。
 */
class AccessFlagsParser:IBytesHandler {
    override fun order() = ParserOrder.AccessFlags

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        val flagsBytes = U2(codeBuf.get(),codeBuf.get())
        classFile.access_flags = flagsBytes
        val flasStr = AccessFlagUtils.toClassAccString(flagsBytes.bytes)
        println("\n  flags:$flasStr")
    }
}