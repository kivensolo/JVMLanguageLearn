package com.kingz.godlike;

/**
 * Copyright(C) 2016, 北京视达科科技有限责任公司
 * All rights reserved.<br>
 * @author King.Z<br>
 * date:  2016/3/20 11:46<br>
 * description: javaDoc测试
 * version: 1.7
 * @version 1.8
 * @since  22
 */
public class Radom4Nums {
    /** comment for a attribute */
    int number;

 //    * <p>@Project：HunanOTT
 //* @Class：DialogActivity
 //* @author：laladin.syd
 //* @author：laladin
 //* @CreateDate：2013-7-4 下午3:50:47
 //* @Modifier：
 //* @ModifyDate：
 //* @Version 1.0.0
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
        int i = 0, t;
        for (t = 0; t <= 3; t++) {
            i = i++;
            System.out.println(i);
        }
    }
}
