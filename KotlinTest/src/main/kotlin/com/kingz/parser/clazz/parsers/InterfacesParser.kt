package com.kingz.parser.clazz.parsers

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.ClassFile
import com.kingz.parser.clazz.U2
import com.kingz.parser.clazz.base.IBytesHandler
import com.kingz.parser.clazz.utils.ParserOrder
import java.nio.ByteBuffer

/**
 * 获取该class实现的接口总数以及该class实现的所有接口
 */
class InterfacesParser:IBytesHandler {
    override fun order()= ParserOrder.InterfacesInfo

    override fun handle(codeBuf: ByteBuffer, classFile: ClassFile) {
        val facesCount = U2(codeBuf.get(), codeBuf.get())
        classFile.interfaces_count = facesCount
        val interfaceCount = facesCount.toInt() //类实现的接口总数,max 65535
        println("Interfaces info:")
        println("  size=$interfaceCount")

        //创建接口表interfaces, 每项都是一个常量索引，指向常量池表中CONSTANT_Class_info结构的常量
        if(interfaceCount > 0){
            val interfacesArray = Array(interfaceCount) { ByteArray(0) }
            classFile.interfaces = interfacesArray
            for(index in 0 until interfaceCount) {
                interfacesArray[index] = byteArrayOf(codeBuf.get(), codeBuf.get())
                println("  Iface_$index = #${HexUtil.readInt(interfacesArray[index])}")
            }
        }
    }
}