package com.reflect.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author: King.Z <br>
 * date:  2018/2/5 11:02 <br>
 * description: 类类型学习「本地类,成员类,合成类具体概念」  <br>
 */
public class ClassTypeTest {

    // 静态成员类
    static class StaticMemberClass {
        int a = print(getClass());
    }

    // 成员类(内部类)
    public class MemberClass {
        int memberInnerCode = print(getClass());
    }

    public static void main(String[] args) {

        // 匿名类
        new Cloneable() {
            int a = print(getClass());
        };

        // 成员类
        MemberClass memberObj = new ClassTypeTest().new MemberClass();

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
        System.out.println("代理proxyInstance:");
        print(proxyInstance.getClass());

        System.out.println("成员类声明变量打印:");
        /*
         * 此遍历会找到 memberInnerCode 和 this$0, this$0就是复合属性。是编译器生成的。
         */
        for (Field field : MemberClass.class.getDeclaredFields()) {
            System.out.println(String.format("[%s] isSynthetic: %s", field.getName(), field.isSynthetic()));
        }
    }

    private static int print(Class<?> clazz) {
        System.out.println(String.format("%s isMems : %s", clazz.getName(), clazz.isMemberClass()));         //成员类
        System.out.println(String.format("%s isLocalClass : %s", clazz.getName(), clazz.isLocalClass()));           //本地类
        System.out.println(String.format("%s isAnonymousClass : %s", clazz.getName(), clazz.isAnonymousClass()));   //匿名类
        System.out.println(String.format("%s isSynthetic: %s", clazz.getName(), clazz.isSynthetic()));              //复合类
        System.out.println("-----------------------------");
        return 0; // 返回值没有用，就为了在成员创建的时候可以调用此函数
    }
}
