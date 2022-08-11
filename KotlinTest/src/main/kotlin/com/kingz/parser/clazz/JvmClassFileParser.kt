package com.kingz.parser.clazz

import java.io.File
import java.io.FileInputStream

/**
 * 解析.class字节码文件
 */
class JvmClassFileParser {

    fun parser(file: File):Boolean{
        val inputStream = FileInputStream(file)
        val bytes = inputStream.readBytes()
        if(bytes.isEmpty()){
            print("Parser faild file is empty.")
           return false
        }


        return true
    }
}