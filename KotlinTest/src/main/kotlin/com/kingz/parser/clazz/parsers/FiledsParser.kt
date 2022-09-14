package com.kingz.parser.clazz.parsers

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.ClassFile
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
        val countBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
        val filedConuts = HexUtil.readInt(countBytes)
        classFile.fields_count = countBytes
        if(filedConuts == 0){
            return
        }

        println("Fileds size:$filedConuts")
        //Create fields table
        val fieldInfoArray = Array<FieldInfo?>(filedConuts){ null }
        for(index in 0 until filedConuts) {
            //循环解析字段数据

            val fieldInfo = FieldInfo()

            val flags = byteArrayOf(codeBuf.get(),codeBuf.get())
            fieldInfo.access_flags = flags
            val fieldAcc = AccessFlagUtils.toFieldsAccString(flags)

            //name_index
            val nameIndexBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
            fieldInfo.name_index = nameIndexBytes
            val nameIndex = HexUtil.readInt(nameIndexBytes)
            val nameInfo = classFile.cp_infos[nameIndex - 1] as CPInfos.CONSTANT_Utf8_Info
            val name = nameInfo.getValue()

            //descriptor_index
            val descriptorBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
            fieldInfo.descriptor_index = descriptorBytes
            val descriptorIndex = HexUtil.readInt(descriptorBytes)
            val descriptorInfo = classFile.cp_infos[descriptorIndex - 1] as CPInfos.CONSTANT_Utf8_Info
            val descriptorValue = descriptorInfo.getValue()

            //attributes info
            val attributesCountBytes = byteArrayOf(codeBuf.get(),codeBuf.get())
            fieldInfo.attributes_count = attributesCountBytes
            val attrCounts = HexUtil.readInt(attributesCountBytes)

            //Create attrbutes table
            val attributesArray = Array(attrCounts){ AttributeInfo() }
            fieldInfo.attributes = attributesArray
            for(attrIndex in 0 until attrCounts) {
                val attrInfo = AttributeInfo()
                attributesArray[attrIndex] = attrInfo

                //解析字段属性
                attrInfo.attribute_name_index = byteArrayOf(codeBuf.get(),codeBuf.get())
                attrInfo.attribute_length = byteArrayOf(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get())
                val attrLen = HexUtil.readInt(attrInfo.attribute_length)
                if(attrLen == 0){
                    return
                }
                // 解析Info[TODO info的数据含义，还未进一步处理]
                val infoBytes = ByteArray(attrLen)
                codeBuf.get(infoBytes,0,attrLen)
                attrInfo.info = infoBytes
            }
            fieldInfoArray[index] = fieldInfo
            println("  Name: cp_info#${index+1} $name:<$descriptorValue>")
            println("  Acc flags: 0x${HexUtil.hexBytesToString(flags)}[$fieldAcc]")
            println("  Attr size: $attrCounts")
        }
    }
}