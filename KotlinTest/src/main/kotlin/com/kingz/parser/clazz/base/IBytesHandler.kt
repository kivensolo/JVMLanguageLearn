package com.kingz.parser.clazz.base

import com.kingz.parser.clazz.ClassFile
import java.nio.ByteBuffer

/**
 * class文件各个解析器的抽象处理层
 */
interface IBytesHandler {

    fun order():Int

    /**
     * 从字节缓存中读取相应的字节数据写入ClassFile对象
     * @param codeBuf  class文件的字节缓冲对象
     * @param classFile class文件结构对象
     */
    @Throws(Exception::class)
    fun handle(codeBuf: ByteBuffer, classFile: ClassFile)

}

