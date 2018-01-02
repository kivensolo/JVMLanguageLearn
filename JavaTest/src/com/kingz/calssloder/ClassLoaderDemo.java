package com.kingz.calssloder;

import java.util.ArrayList;

/**
 * author: King.Z <br>
 * date:  2018/1/2 20:36 <br>
 * description: java ClassLoader学习 <br>
 *
 */
public class ClassLoaderDemo {
    static {
        System.out.println("ClassLoaderDemo is load!");
    }
    public static void main(String[] args) {
        Class<ClassLoaderDemo> demoClass = ClassLoaderDemo.class;
        ClassLoader classLoader = demoClass.getClassLoader();
        System.out.println("loader's name:" + classLoader.toString());
                          //loader's name:sun.misc.Launcher$AppClassLoader@18b4aac2

        Class<ArrayList> listClass = ArrayList.class;
        ClassLoader listLoader = listClass.getClassLoader();
        System.out.println("loader's name:" + listLoader.toString());
        //会报空指针
        //rt.jar     jre/lib目录下的是bootStrapClassLoder 用c++实现 没有java类
    }
}
