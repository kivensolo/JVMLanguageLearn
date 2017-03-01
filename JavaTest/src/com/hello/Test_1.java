package com.hello;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/1/11 11:15 <br>
 * description: XXXXXXX <br>
 */
public class Test_1 {
    public int i,j;

    public void test_m(Test_1 a){
        Test_1 b  = new Test_1();
        b.i = 1;
        b.j = 2;
        a = b;
    }
    public void test_m1(Test_1 a){
        a.i = 1;
        a.j = 2;
    }


    public static void main(String[] args) {
        Test_1 t = new Test_1();
        t.i = 5;
        t.j = 6;
        t.test_m(t);
        System.out.println(t.i+"        "+t.j);
    }
}
