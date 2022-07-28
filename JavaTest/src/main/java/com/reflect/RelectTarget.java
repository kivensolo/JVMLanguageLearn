package com.reflect;

import com.annotation.requestDemo.ReqType;
import com.annotation.requestDemo.ReqTypeEnum;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * date:  2016/9/4 17:42
 * description:反射目标测试类
 */
public class RelectTarget {

    /**
     * 静态内部类
     */
    static class Cat {
        @Override
        public String toString() {
            return "i'm cat!";
        }
    }

    // <editor-fold defaultstate="collapsed" desc="基础数据类型">
    private int num = 555;
    public String str = "hello";
    private final boolean DEBUG_MODE = false;
// </editor-fold>

    private final Cat finalCat = new Cat();

    // <editor-fold defaultstate="collapsed" desc="泛型类类型">
    private ArrayList<String> innerList = new ArrayList<>();
    private List<Cat> ketty;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="native方法">
    private native void testFunc();
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="重载的方法">
    public void numPrint(int num1) {
        System.out.println("num1:" + num1);
    }

    public void numPrint(int num1, int num2) {
        System.out.println("num1:" + num1 + "num2:" + num2);
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="带注解的方法">
    @ReqType({ReqTypeEnum.POST})
    public void findRequestType(String name) {
        System.out.println(" ----- findRequestType() has been invoked!!");
        System.out.println(" ----- name:" + name);
    }
    // </editor-fold>

    /**
     * 没有这个无参构造函数 会导致class.newInstance的时候抛出InstantiationException异常
     * 在Class的getConstructor0方法中会抛出异常
     */
    public RelectTarget() {
        innerList.add("HELLO");
    }

    public RelectTarget(int num, String str) {
        this.num = num;
        //this.str = str;
    }

    public String getDumpStr() {
        return str;
    }

    private int getDumpNum() {
        return num;
    }


    /**
     * Kotlin静态反射方法测试
     */
    void invokeKotlinMethod() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Class<?> classObj = classLoader.loadClass("com.reflect.KtTarget");
            if (KtTarget.class.isAssignableFrom(classObj)) {
                Method method = classObj.getMethod("registerStatic");
                method.setAccessible(true);
                method.invoke(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "RelectTarget{" +
                "num=" + num +
                ", str='" + str + '\'' +
                ", DEBUG_MODE=" + DEBUG_MODE +
                ", cat=" + finalCat +
                ", innerList=" + innerList +
                ", ketty=" + ketty +
                '}';
    }
}
