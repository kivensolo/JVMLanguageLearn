package com.kingz.parser.clazz

import com.kingz.parser.clazz.constant_pool.ConstantPoolInfo

/**
 * A class file consists of a single ClassFile structure:
 */
class ClassFile {
    //class文件标识:0xCAFEBABE。
    var magic:ByteArray = ByteArray(4)
    var minor_version:ByteArray = ByteArray(2)
    var major_version:ByteArray = ByteArray(2)
    // 常量池计数器(常量池实际大小为该值减1)
    var constant_pool_count:ByteArray = ByteArray(2)
    // size = constant_pool_count - 1
    var cp_info = arrayOf<ConstantPoolInfo>()
    var access_flags:ByteArray = ByteArray(2)
    var this_class:ByteArray = ByteArray(2)
    var super_class:ByteArray = ByteArray(2)
    var interfaces_count:ByteArray = ByteArray(2)
    var interfaces:ByteArray = ByteArray(2)
    var fields_count:ByteArray = ByteArray(2)
    // field_info     fields[fields_count];
    var methods_count:ByteArray = ByteArray(2)
    //method_info
    var attributes_count:ByteArray = ByteArray(2)
    //attribute_info



}