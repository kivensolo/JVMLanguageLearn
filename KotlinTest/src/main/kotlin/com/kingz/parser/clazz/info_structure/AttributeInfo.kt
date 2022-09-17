package com.kingz.parser.clazz.info_structure

import com.kingz.parser.clazz.U2
import com.kingz.parser.clazz.U4

/**
 * 属性结构表，属性的类型参考
 * 使用在一下数据结构中：
 * ClassFile, field_info, method_info和 Code_attribute
 */
class AttributeInfo {
    //属性名称，指向常量池某个常量的索引, 可以以此确定属性类型
    var attribute_name_index: U2? = null
    //属性长度，属性info的字节总数
    var attribute_length: U4? = null
    //属性信息
    var info: ByteArray?= null
}