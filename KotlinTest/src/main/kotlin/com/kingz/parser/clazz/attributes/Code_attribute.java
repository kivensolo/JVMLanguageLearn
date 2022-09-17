package com.kingz.parser.clazz.attributes;

import com.kingz.parser.clazz.U2;
import com.kingz.parser.clazz.U4;
import com.kingz.parser.clazz.info_structure.AttributeInfo;

/**
 * Code属性是一种可变长度的属性, 其包含方法的JVM字节码指令和相关辅助信息。
 * 除native、abstract、接口中的非default方法外，其他方法，都要有Code属性。
 *
 * 方法结构中的属性表最多只能有一个Code属性。
 */
public class Code_attribute{
    //指向常量池中"Code"常量的索引
    U2 attribute_name_index;
    //属性的长度(不包含attribute_name_index和attribute_length所占长度)
    U4 attribute_length;

    //方法在执行期间任何点的操作数堆栈的最大深度
    U2 max_stack;
    //局部变量数组最大深度
    U2 max_locals;

    //实现该方法的JVM代码的实际字节长度, 即Code数组的大小。
    U4 code_length;
    byte[] code; //该方法的所有字节码指令

    //异常表中的条目数
    U2 exception_table_length;
    //异常表
    ExceptionTab[] exception_table;

    //属性总数
    U2 attributes_count;
    //属性表, 可能存在的属性如LineNumerTable属性、LocalVariableTable属性。
    AttributeInfo[] attributes;


    /*
     * LineNumerTable属性：
     *  被用来映射源码文件中给定的代码行号对应code[]字节码指令中的哪一部分，
     *  在调试时用到，在方法抛出异常打印异常栈信息也会用到。
     *
     * LocalVariableTable属性： 用来描述code[]中的某个范围内，
     * 局部变量表中某个位置存储的变量的名称是什么，用于与源码文件中局部变量名做映射。
     * 该属性不一定会编译到class文件中，如果没有该属性，
     * 那么查看反编译后的java代码将会使用诸如arg0、arg1、arg2之类的名称代替局部变量的名称。
     */
}
