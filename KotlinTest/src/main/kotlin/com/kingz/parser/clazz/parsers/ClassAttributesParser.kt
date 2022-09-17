package com.kingz.parser.clazz.parsers

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.U2
import com.kingz.parser.clazz.U4
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.cp.CPInfos
import com.kingz.parser.clazz.info_structure.AttributeInfo
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer


/**
 * 字节码文件的属性表解析器
 */
class ClassAttributesParser:IBytesHandler {
    override fun order() = ParserOrder.ClassAttr

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        val attributesCount = U2(codeBuf.get(),codeBuf.get())
        classFile.attributes_count = attributesCount
        val len = attributesCount.toInt()
        if(len == 0){
            println("字节码文件属性总数为0")
            return
        }
        println("字节码文件属性总数为${len}")
        val attributeInfos = Array(len) { AttributeInfo() }
        for(i in 0 until len) {
            val attrInfo = AttributeInfo()
            attributeInfos[i] = attrInfo
            //解析属性数据
            attrInfo.attribute_name_index = U2(codeBuf.get(),codeBuf.get())
            attrInfo.attribute_length = U4(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get())
            val cpIndex = attrInfo.attribute_name_index!!.toInt()
            val attrBytesLen = attrInfo.attribute_length!!.toInt()
            if(attrBytesLen == 0){
                continue
            }
            //解析舒心的info数据
            val infoBytes = ByteArray(attrBytesLen)
            codeBuf.get(infoBytes,0,attrBytesLen)
            attrInfo.info = infoBytes

            val attrName = (classFile.cp_infos[cpIndex-1] as CPInfos.CONSTANT_Utf8_Info).getValue()
            val valueIndex = HexUtil.readInt(attrInfo.info!!)
            val bytesName = (classFile.cp_infos[valueIndex-1] as CPInfos.CONSTANT_Utf8_Info).getValue()
            println(" ╔═══════════════════════════════════════════════════════════════")
            println(" ║Attr[$i]      : cp_info#${cpIndex} <$attrName>")
            println(" ║bytes info   : cp_info#${valueIndex} <$bytesName>")
            println(" ╚═══════════════════════════════════════════════════════════════")

        }
        classFile.attributes = attributeInfos

        val remainingSize = codeBuf.remaining()
        println("字节码剩余字节：$remainingSize")
        if(remainingSize == 0){
            println("class文件正常解析完毕!!")
        }else{
            println("class文件解析异常!剩余未解析字节数：$remainingSize")
        }
    }
}