package com.kingz.parser.clazz.attributes;

import com.kingz.parser.clazz.U2;
import com.kingz.parser.clazz.U4;
import com.kingz.parser.clazz.info_structure.AttributeInfo;

/**
 * Code属性method_info结构中attributes属性表中的一个可变属性
 * Code 属性包含 JVM字节码指令和 方法的辅助信息，
 * 包括实例初始化方法或类或接口初始化方法
 * 在native或者abstract方法中，不能有Code属性，否则在方法属性表中
 * 必须要有一个Code属性。
 */
public class Code_attribute{
    U2 attribute_name_index;
    U4 attribute_length;
    //此方法在执行期间任何点的操作数堆栈的最大深度
    U2 max_stack;
    //在调用此方法时分配的局部变量数组中的局部变量数
    U2 max_locals;

    //实现该方法的JVM代码的实际字节长度和数据。
    U4 code_length;
    byte[] code;

    //Exception_table 表中的条目数
    U2 exception_table_length;
    ExceptionTab[] exception_table;

    U2 attributes_count;
    AttributeInfo[] attributes;
}
