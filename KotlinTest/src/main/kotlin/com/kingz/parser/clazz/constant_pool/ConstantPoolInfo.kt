package com.kingz.parser.clazz.constant_pool

/**
 * 常量池数据区的常量(cp_info)，是常量池数据区基本结构。
 * 数据区由（constant_pool_count - 1）个 cp_info 结构组成
 * 常量遵循以下格式：
 * cp_info {
 *  u1 tag;
 *  u1 info[];
 * }
 * 必须以1个字节开始，表明其实cp_info是哪种类型(字节码一共有14种类型的cp_info)
 * info数组的内容随 tag 的值而变化。
 * 每个标记字节后面必须跟2个或更多字节，提供有关特定常数的信息。
 * 附加信息的格式随标记值的不同而不同。
 * * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4
 */
class ConstantPoolInfo {


}

