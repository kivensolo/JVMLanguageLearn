package com.kingz.parser.clazz.parsers

import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.U2
import com.kingz.parser.clazz.U4
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.cp.CPInfos
import com.kingz.parser.clazz.info_structure.AttributeInfo
import com.kingz.parser.clazz.info_structure.FieldInfo
import com.kingz.parser.clazz.utils.AccessFlagUtils
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer

/**
 * 字段解析器
 * 字段描述符：https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.3.2
 */
class FiledsParser:IBytesHandler {
    override fun order() = ParserOrder.FiledsTable

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        val countBytes = U2(codeBuf.get(),codeBuf.get())
        val filedConuts = countBytes.toInt()
        classFile.fields_count = countBytes
        if(filedConuts == 0){
            return
        }

        println("Fileds counts:$filedConuts")
        //Create fields table
        val fieldInfoArray = Array<FieldInfo?>(filedConuts){ null }
        for(index in 0 until filedConuts) {
            //循环解析字段数据

            val fieldInfo = FieldInfo()

            val flags = U2(codeBuf.get(),codeBuf.get())
            fieldInfo.access_flags = flags
            val fieldAcc = AccessFlagUtils.toFieldsAccString(flags.bytes)

            //name_index
            val nameIdx = U2(codeBuf.get(),codeBuf.get())
            fieldInfo.name_index = nameIdx
            val nameIndex = nameIdx.toInt()
            val nameInfo = classFile.cp_infos[nameIndex - 1] as CPInfos.CONSTANT_Utf8_Info
            val name = nameInfo.getValue()

            //descriptor_index
            val desc = U2(codeBuf.get(),codeBuf.get())
            fieldInfo.descriptor_index = desc
            val descriptorIndex = desc.toInt()
            val descriptorInfo = classFile.cp_infos[descriptorIndex - 1] as CPInfos.CONSTANT_Utf8_Info
            val descriptorValue = descriptorInfo.getValue()

            //attributes info
            val attrCount = U2(codeBuf.get(),codeBuf.get())
            fieldInfo.attributes_count = attrCount
            val attrCounts = attrCount.toInt()

            //Create attrbutes table
            val attributesArray = Array(attrCounts){ AttributeInfo() }
            fieldInfo.attributes = attributesArray
            for(attrIndex in 0 until attrCounts) {
                val attrInfo = AttributeInfo()
                attributesArray[attrIndex] = attrInfo

                //解析属性
                attrInfo.attribute_name_index = U2(codeBuf.get(),codeBuf.get())
                attrInfo.attribute_length = U4(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get())
                val attrLen = attrInfo.attribute_length!!.toInt()
                if(attrLen == 0){
                    return
                }
                // 解析Info[TODO info的数据含义，还未进一步处理]
                val infoBytes = ByteArray(attrLen)
                codeBuf.get(infoBytes,0,attrLen)
                attrInfo.info = infoBytes
            }
            fieldInfoArray[index] = fieldInfo
            println("  Name     : cp_info#${index+1} $name:<$descriptorValue>")
            println("  Acc flags: 0x${flags.toHexString()}[$fieldAcc]")
            println("  Attr size: $attrCounts")
        }
    }
}