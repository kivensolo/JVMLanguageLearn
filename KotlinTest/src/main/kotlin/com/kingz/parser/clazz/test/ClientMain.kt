package com.kingz.parser.clazz.test

import com.kingz.parser.clazz.JvmClassFileParser
import java.io.File

fun main(args: Array<String>) {
    val file = File("D:${File.separator}AClass.class")
    JvmClassFileParser().parser(file)
}