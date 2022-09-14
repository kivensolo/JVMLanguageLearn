package com.kingz.parser.clazz.info_structure

/**
 * 属性结构表
 */
class AttributeInfo {
    //属性名称，指向常量池某个常量的索引, 可以以此确定属性类型
    var attribute_name_index = ByteArray(2)
    //属性长度，属性info的字节总数
    var attribute_length = ByteArray(4)
    //属性信息
    var info: ByteArray?= null
}