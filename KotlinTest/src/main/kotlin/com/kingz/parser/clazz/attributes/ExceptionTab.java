package com.kingz.parser.clazz.attributes;

import com.kingz.parser.clazz.U2;

/**
 * 异常表的结构
 * 每一条数据就描述了code[]中的一个异常处理程序;
 * Exception_table 数组中处理程序的顺序是有其意义的;
 */
public class ExceptionTab {
   //try的开始位置，在code[]中的索引
   U2 start_pc;
   //try的结束位置，在code[]中的索引
   U2 end_pc;
   //异常处理的起点，在code[]中的索引
   U2 handler_pc;
   /**
    * 为0相当于finally块，不为0
    * 指向常量池中某个CONSTANT_Class_info常量索引，表示需要捕获的异常。
    * 只有[start_pc,end_pc)范围内抛出的异常是指定的类或者子类的实例，
    * 才会跳转到handler_pc指向的字节码指令继续执行。
    */
   U2 catch_type;
}
