package com.kingz.parser.clazz.parsers

import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.U4
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.base.Parser
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer

/**
 * Magic number parser
 */
@Parser(name = "Magic")
class MagicParser : IBytesHandler {
    override fun order() = ParserOrder.Magic

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        classFile.magic = U4(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get())
        val maigcHex = classFile.magic!!.toHexString()
        if("CAFEBABE" != maigcHex){
            throw Exception("This is not a Class file !!!")
        }
        println("魔数校验成功!")
    }
}