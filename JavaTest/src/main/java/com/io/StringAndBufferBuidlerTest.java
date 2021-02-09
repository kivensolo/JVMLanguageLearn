package com.io;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/1/23 11:13 <br>
 * description: XXXXXXX <br>
 */
public class StringAndBufferBuidlerTest {
    public static void main(String[] args) {
        long current = System.currentTimeMillis();
        //String str1 = "this" + "is-a" + "test String";
        String a = "this";
        String b = "is-a" ;
        String c = "test String";
        String str1 =  a + b + c;
        long dec = System.currentTimeMillis() - current;
        System.out.println("str=" + str1 + "  ;dec = " + dec);
    }

}
