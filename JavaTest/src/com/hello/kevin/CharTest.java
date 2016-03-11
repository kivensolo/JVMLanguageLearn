package com.hello.kevin;

/**
 * description:测试一个char汉字的字符
 * 字节（Byte）：字节是计算机信息技术用于计量存储容量和传输容量的一种计量单位，
 * 1个字节等于8位二进制，它是一个8位的二进制数，是一个很具体的存储空间。
 * 字符(Char)：人们使用的记号，抽象意义上的一个符号。 '1'， '中'， 'a'， '$'， '￥'， ……
 * 字符就不得不提ANSI及UNICODE两种不同的编码方式标准，ANSI中的字符采用8bit，而UNICODE中的字符采用16bit，
 * 按照ANSI编码标准，标点符号、数字、大小写字母都占一个字节，汉字占2个字节。按照UNICODE标准所有字符都占2个字节。
 * 详见：http://blog.sina.com.cn/s/blog_6ede15b10100nrxp.html
 *
 * GBK中，一个汉字，要2个字节，1字节 = 8bit，
 * 所以表示的长度是16位。
 * UTF-8至少需要3个字节。
 */
public class CharTest {
    public static void main(String[] args) {
        char c = 'a';
        System.out.println(c);
    }
}