@file:Suppress("PropertyName")

package com.kingz.parser.clazz

import com.kingz.parser.clazz.cp.CPInfos

/**
 * A class file consists of a single ClassFile structure:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html
 *
 * com.sun.java.util.jar.pack.PackageReader 可以参考
 */
class ClassFile {

    var magic:ByteArray = ByteArray(4)          //class文件标识:0xCAFEBABE。
    var minor_version:ByteArray = ByteArray(2)  //Minor Version
    var major_version:ByteArray = ByteArray(2)  //Major Version 十进制52对应的JDK版本为1.8

    // 常量池计数器(常量池实际大小为该值减1  排除了下标0)
    var constant_pool_count:ByteArray = ByteArray(2)
    // 常量池数据区数据 size = constant_pool_count - 1
    var cp_infos = arrayOfNulls<CPInfos.CPInfo>(0)

    // 访问标志: 类还是接口, 以及是否被 Public、Abstract、Final 等修饰符修饰
    var access_flags:ByteArray = ByteArray(2)
    // 当前类的全限定名，值为常量池中的索引值
    var this_class:ByteArray = ByteArray(2)
    // 父类的全限定名,值也是在常量池中的索引值
    var super_class:ByteArray = ByteArray(2)

    // 类的接口计数器
    var interfaces_count:ByteArray = ByteArray(2)
    var interfaces:ByteArray = ByteArray(2)

    var fields_count:ByteArray = ByteArray(2)      //字段个数
    // field_info     fields[fields_count];            //每个字段的详细信息 field_info

    var methods_count:ByteArray = ByteArray(2)
    //method_info

    var attributes_count:ByteArray = ByteArray(2)
    //attribute_info
}