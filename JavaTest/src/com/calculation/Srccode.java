package com.calculation;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/1/11 11:15 <br>
 * description: 原码 反码 补码复习 <br>
 */
public class Srccode {

    public static void main(String[] args) {
        //11: 原码:00000000 00000000 00000000 00001011
        //    反码:11111111 11111111 11111111 11110100
        //    补码:11111111 11111111 11111111 11110110
        int i = -11; //11111111 11111111 11111111 11110110
        System.out.println(Integer.valueOf("-11",2));
        i = i >> 2;  //11111111 11111111 11111111 11111101  | 10
        //减1:: .....00
        //原码:......11  -----> 3
        System.out.println(">> 1 : " + i);
        i = i >> 1;
        System.out.println(">> 1 :" + i);
    }
}
