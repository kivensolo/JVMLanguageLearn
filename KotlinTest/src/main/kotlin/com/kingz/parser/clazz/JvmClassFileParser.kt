package com.kingz.parser.clazz

import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.parsers.*
import com.kingz.parser.clazz.utils.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.nio.ByteBuffer


/**
 * JVM Class code parser.
 */
class JvmClassFileParser {

    companion object{
        private val handlers: MutableList<IBytesHandler> = mutableListOf()
        init {
            handlers.add(MagicParser())
            handlers.add(VersionParser())
            handlers.add(ConstantPoolParser())
            handlers.add(AccessFlagsParser())
            handlers.add(ThisAndSuperParser())
            handlers.add(InterfacesParser())
            handlers.add(FiledsParser())
            handlers.add(MethodsParser())
            // 解析器排序，要按顺序调用
            handlers.sortWith((Comparator.comparingInt(IBytesHandler::order)))
        }

        @JvmStatic
        fun analysis(file:File):ClassFile{
            if(!file.exists()){
                throw IllegalArgumentException("File is not exists! ${file.path}")
            }
            val bins = BufferedInputStream(FileInputStream(file))
            if(bins.isEmpty){
                throw IllegalAccessException("Can't read byte code from file!")
            }
            val classByteBuffer = ByteBuffer.wrap(bins.readBytes())
            classByteBuffer.position(0) //reset read pooint
            return readBuffer(classByteBuffer)
        }

        private fun readBuffer(srcBuffer: ByteBuffer):ClassFile{
            val classFile = ClassFile()
            handlers.forEach {
                it.handle(srcBuffer,classFile)
            }
            return classFile
        }
    }
}