package com.string.finaler;

/**
 * 字符串final特性测试
 */
class StringFinalTest {
    public static void main(String[] args) {
        String a = "test";
        String b = "test";
        b = b.toUpperCase();
        System.out.println("a="+a+"; b="+b);
    }
}