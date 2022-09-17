package com.kingz.parser.clazz.utils

/**
 * 字节码解析器的顺序定义
 */
object ParserOrder {
    const val Magic = 0
    const val Version = 1
    const val ConstantPool = 2
    const val AccessFlags = 3
    const val ThisAndSuper = 4
    const val InterfacesInfo = 5
    const val FiledsTable = 6
    const val Methods = 7
    const val ClassAttr = 8
}