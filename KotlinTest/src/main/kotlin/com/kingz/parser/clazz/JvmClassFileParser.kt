package com.kingz.parser.clazz

import com.kingz.kt.utils.HexUtil
import com.kingz.parser.clazz.constant_pool.CPInfosParser
import com.kingz.parser.clazz.utils.BufferedInputStream
import java.io.File
import java.io.FileInputStream

/**
 * 解析.class字节码文件
 */
class JvmClassFileParser {
    fun parser(file: File):Boolean{
        val classFile = ClassFile()
        val ins = FileInputStream(file)
        val bins = BufferedInputStream(ins, 64 * 1024)
        if(bins.isEmpty){
            print("Parser faild file is empty.")
           return false
        }
        // Read magic TODO 使用职责链处理。
        bins.read(classFile.magic)
        val magic = "0x${HexUtil.hexBytesToString(classFile.magic)}"
        if("0xCAFEBABE" == magic){
            println("This is a class file.")
            bins.read(classFile.minor_version)
            GeneralInformation.minorVersion = HexUtil.readInt(classFile.minor_version)
            bins.read(classFile.major_version)
            GeneralInformation.majorVersion = HexUtil.readInt(classFile.major_version)
            print(GeneralInformation)
            val cpInfosParser = CPInfosParser().parser(bins)

            return true
        }
        return false
    }
}