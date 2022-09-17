package com.kingz.parser.clazz.parsers

import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.U2
import com.kingz.parser.clazz.U4
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.cp.CPInfos
import com.kingz.parser.clazz.info_structure.AttributeInfo
import com.kingz.parser.clazz.info_structure.MethodInfo
import com.kingz.parser.clazz.utils.AccessFlagUtils
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer

/**
 * 方法表解析器
 * 方法描述符：
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.3.3
 */
class MethodsParser:IBytesHandler {
    override fun order() = ParserOrder.Methods

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        val counts = U2(codeBuf.get(),codeBuf.get())
        val methodsConuts = counts.toInt()
        classFile.methods_count = counts
        if(methodsConuts == 0){
            return
        }

        println("Method counts:$methodsConuts")
        //Create method table
        val methodsInfoArray = Array(methodsConuts){ MethodInfo() }
        for(index in 0 until methodsConuts) {
            //循环解析方法数据
            val _mInfo = MethodInfo()

            val flags = U2(codeBuf.get(),codeBuf.get())
            _mInfo.access_flags = flags
            val fieldAcc = AccessFlagUtils.toFieldsAccString(flags.bytes)

            //name_index
            val nameIdx = U2(codeBuf.get(),codeBuf.get())
            _mInfo.name_index = nameIdx
            val nameIndex = nameIdx.toInt()
            val nameInfo = classFile.cp_infos[nameIndex - 1] as CPInfos.CONSTANT_Utf8_Info
            val name = nameInfo.getValue()

            //descriptor_index
            val desc = U2(codeBuf.get(),codeBuf.get())
            _mInfo.descriptor_index = desc
            val descriptorIndex = desc.toInt()
            val descriptorInfo = classFile.cp_infos[descriptorIndex - 1] as CPInfos.CONSTANT_Utf8_Info
            val descriptorValue = descriptorInfo.getValue()

            //attributes info
            val attrCount = U2(codeBuf.get(),codeBuf.get())
            _mInfo.attributes_count = attrCount
            val attrCounts = attrCount.toInt()

            //Create attrbutes table
            val attributesArray = Array(attrCounts){ AttributeInfo() }
            _mInfo.attributes = attributesArray
            for(attrIndex in 0 until attrCounts) {
                val attrInfo = AttributeInfo()
                attributesArray[attrIndex] = attrInfo

                //解析字段属性
                attrInfo.attribute_name_index = U2(codeBuf.get(),codeBuf.get())
                attrInfo.attribute_length = U4(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get())
                val attrLen = attrInfo.attribute_length!!.toInt()
                if(attrLen == 0){
                    return
                }
                // 解析Info[TODO info的数据含义，还未进一步处理  比如CODE下面有 lineNumberTable等]
                val infoBytes = ByteArray(attrLen)
                codeBuf.get(infoBytes,0,attrLen)
                attrInfo.info = infoBytes
            }
            methodsInfoArray[index] = _mInfo
            println(" ╔═══════════════════════════════════════════════════════════════")
            println(" ║Name     : cp_info#${nameIndex} $name:$descriptorValue")
            println(" ║Acc flags: 0x${flags.toHexString()}[$fieldAcc]")
            println(" ║Attr counts: $attrCounts")
            for(_index in 0 until attrCounts) {
                val attr = _mInfo.attributes!![_index]
                val attrTypeIndex = attr.attribute_name_index!!.toInt()
                val attrType = (classFile.cp_infos[attrTypeIndex - 1] as CPInfos.CONSTANT_Utf8_Info).getValue()
                val attrLen = attr.attribute_length!!.toInt()
                println(" ╠--- Attr[$_index]: cp_info#$attrTypeIndex <${attrType.toUpperCase()}> 数据长度: $attrLen")
            }
            println(" ╚═══════════════════════════════════════════════════════════════")
        }
        classFile.methods = methodsInfoArray
    }
}