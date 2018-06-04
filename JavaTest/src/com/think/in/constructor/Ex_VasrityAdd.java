package com.think.in.constructor;

/**
 * author: King.Z <br>
 * date:  2018/6/4 21:58 <br>
 * description: 静态内存区域的复习 <br>
 */
public class Ex_VasrityAdd {
    private static int old = 1;
    public static void add(){
        old ++;
    }

    public static void main(String[] args) {
        Ex_VasrityAdd ex_vasrityAdd = new Ex_VasrityAdd();
        Ex_VasrityAdd ex_vasrityAddV2 = new Ex_VasrityAdd();
        ex_vasrityAdd.add();
        ex_vasrityAddV2.add();
        System.out.println(old); //问：打印多少？
    }
}
