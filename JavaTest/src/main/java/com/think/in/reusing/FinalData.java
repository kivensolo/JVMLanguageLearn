package com.think.in.reusing;

import java.util.Random;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/7/13 23:09
 * description:
 *          final关键字用于数据
 *  -------:static:强调只有一份
 *
 */
class Value {
    int i; // Package access
    public Value(int i) {
        this.i = i;
    }
}

public class FinalData {
    private static Random rand = new Random(47);
    private String id;

    public FinalData(String id) {
        this.id = id;
    }
    // 常量:编译期间确定了其值
    private final int valueOne = 9;             //数据为final，无法修改
    private static final int VALUE_TWO = 99;

    // static:强调只有一份  final：说明是一个常量
    public static final int VALUE_THREE = 39;

    //不能在编译期间确定值的:
    private final int i4 = rand.nextInt(20);  //运行时来初始化数据
    static final int INT_5 = rand.nextInt(20);


    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);   //v2的值可以被修改，因为final意味着无法将v2再次指向另一个新的对象
    private static final Value VAL_3 = new Value(33);

    // Arrays:
    private final int[] a = {1, 2, 3, 4, 5, 6};

    public String toString() {
        return id + ": " + "i4 = " + i4 + ", INT_5 = " + INT_5;
    }


    public static void main(String[] args) {
        FinalData fd1 = new FinalData("fd1");
//        fd1.valueOne++;           // Error: can't change value
        fd1.v2.i++;                 // Object isn't constant!
        fd1.v1 = new Value(9);      // OK -- not final
        for (int i = 0; i < fd1.a.length; i++){
            fd1.a[i]++;             // Object isn't constant!
        }
        //! fd1.v2 = new Value(0); // Error: v2无法指向另一个新的对象
        fd1.v2.i = 6;               //v2的值可以被改变
        //! fd1.VAL_3 = new Value(1); // change reference
        //! fd1.a = new int[3];
        System.out.println(fd1);
        System.out.println("Creating new FinalData");
        FinalData fd2 = new FinalData("fd2");
        System.out.println(fd1);
        System.out.println(fd2);
    }

}
