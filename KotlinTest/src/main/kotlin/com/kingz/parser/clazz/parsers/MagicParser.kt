package com.kingz.parser.clazz.parsers

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.base.Parser
import java.nio.ByteBuffer

/**
 * Magic number parser
 */
@Parser(name = "Magic")
class MagicParser : IBytesHandler {
    override fun order() = 0

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        val bytes = byteArrayOf(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get())
        classFile.magic = bytes
        val maigcHex = HexUtil.hexBytesToString(bytes)
        if("CAFEBABE" != maigcHex){
            throw Exception("This is not a Class file !!!")
        }
        println("魔数校验成功!")
    }
}