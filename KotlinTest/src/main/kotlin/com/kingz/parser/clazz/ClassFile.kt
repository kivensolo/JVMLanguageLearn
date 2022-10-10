@file:Suppress("PropertyName")

package com.kingz.parser.clazz

import com.kingz.parser.clazz.cp.CPInfos
import com.kingz.parser.clazz.info_structure.AttributeInfo
import com.kingz.parser.clazz.info_structure.FieldInfo
import com.kingz.parser.clazz.info_structure.MethodInfo

/**
 * A class file consists of a single ClassFile structure:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html
 */
class ClassFile {
    var magic:U4?= null          //class文件标识:0xCAFEBABE。
    var minor_version:U2?= null  //Minor Version
    var major_version:U2?= null  //Major Version 十进制52对应的JDK版本为1.8

    // 常量池计数器(常量池实际大小为该值减1  排除了下标0)
    var constant_pool_count:U2?= null
    // 常量池数据区数据 size = constant_pool_count - 1
    var cp_infos = arrayOfNulls<CPInfos.CPInfo>(0)

    // 访问标志: 类还是接口, 以及是否被 Public、Abstract、Final 等修饰符修饰
    var access_flags:U2?= null
    // 当前类的全限定名，值为常量池中的索引值
    var this_class:U2?= null
    // 父类的全限定名,值也是在常量池中的索引值
    var super_class:U2?= null

    // 类的接口计数器
    var interfaces_count:U2?= null
    var interfaces:Array<ByteArray>? = null

    // 字段
    var fields_count:U2?= null
    var fields:Array<FieldInfo>? = null

    //方法
    var methods_count:U2?= null
    var methods:Array<MethodInfo>? = null

    //属性
    var attributes_count:U2?= null
    var attributes:Array<AttributeInfo>? = null
}