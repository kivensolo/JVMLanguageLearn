package com.kingz.parser.clazz.parsers

import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.U2
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.cp.CPInfos
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
class ThisAndSuperParser : IBytesHandler {
    override fun order() = ParserOrder.ThisAndSuper
    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        println("This and Super ClassParser:")
        //Find the index value of this and super class.
        val thisClass = U2(codeBuf.get(), codeBuf.get())
        classFile.this_class = thisClass
        val superClass = U2(codeBuf.get(), codeBuf.get())
        classFile.super_class = superClass

        val thisClassIndex = superClass.toInt()
        val classCpInfo = classFile.cp_infos[thisClassIndex - 1]
        val indexOfThisClass = (classCpInfo as CPInfos.CONSTANT_Class_Info).getIndexValue()
        val utf8CpInfo = classFile.cp_infos[indexOfThisClass - 1]
        val thisClassName = (utf8CpInfo as CPInfos.CONSTANT_Utf8_Info).getValue()
        println("  this_class = #${thisClass.toInt()}  //$thisClassName")

        val superClassIndex = superClass.toInt()
        val classCpInfo2 = classFile.cp_infos[superClassIndex - 1]
        val indexOfSuperClass = (classCpInfo2 as CPInfos.CONSTANT_Class_Info).getIndexValue()
        val utf8CpInfo2 = classFile.cp_infos[indexOfSuperClass - 1]
        val thisSuperName = (utf8CpInfo2 as CPInfos.CONSTANT_Utf8_Info).getValue()
        println("  super_class = #${superClass.toInt()} //$thisSuperName")
    }
}