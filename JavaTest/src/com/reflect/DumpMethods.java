package com.reflect;

import java.lang.reflect.Method;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/4/24 22:26
 * description:反射:获取类的方法名
 */
public class DumpMethods {
    public static void main(String[] args) {
        try {
            String name = DumpMethods.class.getSimpleName();
            Class c = Class.forName("java.lang.String");
            Method[] mthods = c.getDeclaredMethods();
            for (int i = 0; i < mthods.length; i++) {
                System.out.println(mthods[i].toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testmethods(){

    }
}
