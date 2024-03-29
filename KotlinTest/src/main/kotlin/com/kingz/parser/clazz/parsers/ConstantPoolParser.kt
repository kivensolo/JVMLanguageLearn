package com.kingz.parser.clazz.parsers

import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.U2
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.cp.CPInfos
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer

/**
 * 常量池解析器, 主要解析以下毛绒破魔弓：
 * 计数器+数据区数据
 */
class ConstantPoolParser : IBytesHandler {
    override fun order() = ParserOrder.ConstantPool

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        println("解析常量池信息>>>>>>")
        val cpCountsU2 = U2(codeBuf.get(), codeBuf.get())
        classFile.constant_pool_count = cpCountsU2
        //常量池计数器大小 等于常量池数据区条目个数+1
        val poolSize = cpCountsU2.toInt() - 1
        classFile.cp_infos = arrayOfNulls(poolSize)
        println("Constant Pool($poolSize):")

        //Analytical constant pool in sequence of the constants
        for (index in 0 until poolSize) {
            val tagByte = codeBuf.get()
            val cpInfo = CPInfos.createCpInfo(tagByte)
            cpInfo.read(codeBuf)

            println(String.format("%3s = %s","#"+(index+1),cpInfo.toString()))
            classFile.cp_infos[index] = cpInfo
        }
        println("<<<<<<<常量池解析完毕")
    }
}