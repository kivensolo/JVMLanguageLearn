package com.kingz.godlike;

/**
 * Copyright(C) 2016, Starcor
 * All rights reserved.
 * author: King.Z
 * date:  2016/3/20 11:46
 * description: javaDoc测试
 * <p>@Project：HunanOTT
 * @Class：DialogActivity
 * @author：laladin.syd
 * @CreateDate：2013-7-4 下午3:50:47
 * @Modifier：
 * @ModifyDate：
 * @Version 1.0.0
 */
public class Radom4Nums {
    /** comment for a attribute */
    int number;

    /**
     * comment for a constructor.
     * @param number
     * @see #number
     * @see #main(String[])
     * @see #getNum(int, boolean)
     * @exception NullPointerException throw
     *            when parameter XXXX is null
     *            @deprecated
     */
    public Radom4Nums(int number) {
        this.number = number;
    }

    public int getNum(int index,boolean add){
        return number;
    }

    /** comment for a method */
    public static void main(String[] args) {
        int num = (int) (Math.random()*9000 + 1000);
        System.out.println("num = "+ num);
    }
}
