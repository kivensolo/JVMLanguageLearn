package com.kingz.parser.clazz.parsers

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.ClassFile
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
        val countBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
        val methodsConuts = HexUtil.readInt(countBytes)
        classFile.fields_count = countBytes
        if(methodsConuts == 0){
            return
        }

        println("Method counts:$methodsConuts")
        //Create method table
        val methodInfoArray = Array<MethodInfo?>(methodsConuts){ null }
        for(index in 0 until methodsConuts) {
            //循环解析方法数据
            val _mInfo = MethodInfo()

            val flags = byteArrayOf(codeBuf.get(),codeBuf.get())
            _mInfo.access_flags = flags
            val fieldAcc = AccessFlagUtils.toFieldsAccString(flags)

            //name_index
            val nameIndexBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
            _mInfo.name_index = nameIndexBytes
            val nameIndex = HexUtil.readInt(nameIndexBytes)
            val nameInfo = classFile.cp_infos[nameIndex - 1] as CPInfos.CONSTANT_Utf8_Info
            val name = nameInfo.getValue()

            //descriptor_index
            val descriptorBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
            _mInfo.descriptor_index = descriptorBytes
            val descriptorIndex = HexUtil.readInt(descriptorBytes)
            val descriptorInfo = classFile.cp_infos[descriptorIndex - 1] as CPInfos.CONSTANT_Utf8_Info
            val descriptorValue = descriptorInfo.getValue()

            //attributes info
            val attributesCountBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
            _mInfo.attributes_count = attributesCountBytes
            val attrCounts = HexUtil.readInt(attributesCountBytes)

            //Create attrbutes table
            val attributesArray = Array(attrCounts){ AttributeInfo() }
            _mInfo.attributes = attributesArray
            for(attrIndex in 0 until attrCounts) {
                val attrInfo = AttributeInfo()
                attributesArray[attrIndex] = attrInfo

                //解析字段属性
                attrInfo.attribute_name_index = byteArrayOf(codeBuf.get(),codeBuf.get())
                val attrTypeIndex = HexUtil.readInt(attrInfo.attribute_name_index)
                val attrType = (classFile.cp_infos[attrTypeIndex - 1] as CPInfos.CONSTANT_Utf8_Info).getValue()
                attrInfo.attribute_length = byteArrayOf(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get())
                val attrLen = HexUtil.readInt(attrInfo.attribute_length)
                println("  包含方法属性: ${attrType.toUpperCase()} 数据长度: $attrLen")
                if(attrLen == 0){
                    return
                }
                // 解析Info[TODO info的数据含义，还未进一步处理]
                val infoBytes = ByteArray(attrLen)
                codeBuf.get(infoBytes,0,attrLen)
                attrInfo.info = infoBytes
            }
            methodInfoArray[index] = _mInfo
            println("  Name     : cp_info#${index+1} $name:$descriptorValue")
            println("  Acc flags: 0x${HexUtil.hexBytesToString(flags)}[$fieldAcc]")
            println("  Attr counts: $attrCounts")
        }
    }
}