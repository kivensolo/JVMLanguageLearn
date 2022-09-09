package com.kingz.parser.clazz.parsers

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.cp.CPInfos
import java.nio.ByteBuffer

/**
 * 常量池解析器, 主要解析以下毛绒破魔弓：
 * 计数器+数据区数据
 */
class ConstantPoolParser : IBytesHandler {
    override fun order() = 2

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        val bytes = byteArrayOf(codeBuf.get(), codeBuf.get())
        //常量池计数器大小 等于常量池数据区条目个数+1
        val cpCount = HexUtil.readInt(bytes)
        val poolSize = cpCount - 1
        classFile.constant_pool_count = bytes
        classFile.cp_infos = arrayOfNulls(poolSize)
        println("\nConstant Pool($poolSize):")

        //Analytical constant pool in sequence of the constants
        for (index in 0 until poolSize) {
            val tagByte = codeBuf.get()
            val cpInfo = CPInfos.createCpInfo(tagByte)
            cpInfo.read(codeBuf)

            println(String.format("%3s = %s","#"+(index+1),cpInfo.toString()))
            classFile.cp_infos[index] = cpInfo
        }
    }
}