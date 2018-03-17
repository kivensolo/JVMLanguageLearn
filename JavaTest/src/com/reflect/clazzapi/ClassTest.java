package com.reflect.clazzapi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright(C) 2018, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2018/2/5 11:02 <br>
 * description: 「本地类,成员类,合成类具体概念」  <br>
 */
public class ClassTest {

    static class StaticMemberClass {
        int a = print(getClass());
    }

    public class MemberClass {
        int a = print(getClass());
    }

    public static void main(String[] args) {

        // 匿名类
        new Cloneable() {
            int a = print(getClass());
        };

        // 成员类
        new ClassTest().new MemberClass();

        // 静态成员类
        new StaticMemberClass();

        // 局部类
        class LocalClass {
            int a = print(getClass());
        }

        new LocalClass();

        final Object proxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Cloneable.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        print(proxyInstance.getClass());

        for (Field field : MemberClass.class.getDeclaredFields()) {
            System.out.println(String.format("%s isSynthetic: %s", field.getName(), field.isSynthetic()));
        }
    }

    private static int print(Class<?> clazz) {
        System.out.println(String.format("%s isMemberClass : %s", clazz.getName(), clazz.isMemberClass()));         //成员类
        System.out.println(String.format("%s isLocalClass : %s", clazz.getName(), clazz.isLocalClass()));           //本地类
        System.out.println(String.format("%s isAnonymousClass : %s", clazz.getName(), clazz.isAnonymousClass()));   //匿名类
        System.out.println(String.format("%s isSynthetic: %s", clazz.getName(), clazz.isSynthetic()));              //复合类
        System.out.println("-----------------------------");
        return 0; // 返回值没有用，就为了在成员创建的时候可以调用此函数
    }
}
