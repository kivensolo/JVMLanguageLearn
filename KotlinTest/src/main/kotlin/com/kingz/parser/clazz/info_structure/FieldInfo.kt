package com.kingz.parser.clazz.info_structure

import com.kingz.parser.clazz.U2

/**
 * field_info structure
 * field_info {
 *     u2             access_flags;
 *     u2             name_index;
 *     u2             descriptor_index;
 *     u2             attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 */
open class FieldInfo {
    var access_flags: U2? = null
    //字段名,指向常量池某个Utf8_info类型的索引
    var name_index: U2? = null
    //类型描述符，指向常量池某个Utf8_info类型的索引
    var descriptor_index: U2? = null

    //属性总数(可以为0个或者多个)
    var attributes_count: U2? = null
    //属性(实际数量为attributes_count)
//    var attributes = arrayOfNulls<AttributeInfo>(0)
    var attributes: Array<AttributeInfo>?= null

    override fun toString(): String {
        //TODO 字符串打印
        return super.toString()
    }
}