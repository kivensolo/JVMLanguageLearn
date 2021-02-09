package com.reflect;

public class JavaTarget {
    static{
        System.out.println("static loading ----> JavaTargetClass");
    }

    public static void registerStatic() {
        System.out.println("static register.");
    }

    public void register(){
        System.out.println("normal register");
    }
}
