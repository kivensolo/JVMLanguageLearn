package com.kingz.parser.test

import com.kingz.parser.clazz.JvmClassFileParser
import java.io.File

//https://www.jianshu.com/p/196c64423af2
fun main(args: Array<String>) {
    val file = File("E:\\local\\JavaCodeCompileDemo.class")
    JvmClassFileParser.analysis(file)
}