package com.think.in.reusing;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/7/13 23:36
 * description: 联系18：创建一个含有static final 域和 final域的类，说明两者间的区别
 */
public class Practice_18 {
    private static final int NUM_A = 4;
    private final int num_b = 6;
    //NUM_A 有且只有一个实例，且值不能被改变
    public static void main(String[] args) {
        System.out.println(""+NUM_A);
    }
    private void useData(){
        System.out.println(""+num_b);
    }
}
class KingZ{

}
