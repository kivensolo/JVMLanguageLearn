package com.string;

/**
 * @author King.Z
 * @version ${STUB}
 * @since 2019/8/5 20:28 <br>
 * 测试JVM实例化字符串常量池的特点
 */
public class StringOfCreate {
    public static void main(String[] args) {
        test_2();
    }

    /**
     * 测试使用 '==' 判断使用了常量池中相同的字符串结果。
     * 个人分析:相同， 理由 ： == 比较的是地址，str1和str2在栈中，分别有其内存地址，
     * 但地址都指向常量池中同一个字符串地址。
     */
    private static void test_1(){
        String str1 = "hello";
        String str2 = "hello";
        System.out.println("str1 == str2 ----> " + (str1 == str2)); // true
    }

    /**
     * 字符串对象内部是用字符数组存储的。
     */
    private static void test_2(){
        String m = "hello,world";
        String n = "hello,world";
        // new一个String会生成一个新的字符串，
        // 但内部的字符数组引用着m内部的字符数组，所以值相同
        // 但内存地址不同
        String u = new String(m);  //这样new  u和m用==比较就不相等
        // 但是编译器会提示这是多余的操作，所以应该String u = m;  这样复用缓存
        String v = new String("hello,world");
        System.out.println("u == m ----> " + (u == m)); // false

        // 所以， m,u,v都是不同的对象  m和n相同
    }
}
