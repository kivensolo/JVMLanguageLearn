package com.kingz.parser.clazz.attributes;

import com.kingz.parser.clazz.U2;

/**
 * 异常表的结构
 * 每一条数据就描述了code[]中的一个异常处理程序;
 * Exception_table 数组中处理程序的顺序是有其意义的;
 */
public class ExceptionTab {
   U2 start_pc;
   U2 end_pc;
   U2 handler_pc;
   U2 catch_type;
}
