package com.hello.kevin;

import java.util.Scanner;

public class Test_24String_reverse {

    /**
     * @Description： 输入一串数字  翻转后输出
     * @param args
     * @Return void
     */
    StringReverseMethods methods = new StringReverseMethods();

    public static void main(String[] args) {
        Test_24String_reverse bb = new Test_24String_reverse();
        System.out.println("请输入：");
        Scanner in = new Scanner(System.in);
        long s = in.nextLong();
        String str = Long.toString(s);
        bb.methods.Reverse1(str);  //方法一  转为char[]
//        bb.methods.Reverse2(str);  //方法二  用StringBuffer的reverse方法
    }

    class StringReverseMethods {
        //内部类

        /* 用char[] 和循环 */
        public void Reverse1(String s) {
            char cha[] = s.toCharArray();
            String reverse = "";
            for (int i = cha.length - 1; i >= 0; i--) {
                reverse += cha[i];
            }
            System.out.println("翻转后的字符串：" + reverse);
        }

        /* 用StringBuffer  */
        public void Reverse2(String s) {
            StringBuffer buffer = new StringBuffer(s);
            String result = buffer.reverse().toString();
            System.out.println("翻转后的字符串：" + result);
        }

    }
}

