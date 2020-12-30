package com.reflect;

import com.annotation.requestDemo.ReqType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author: King.Z
 * date:  2016/4/24 22:26
 * description:反射:获取类的方法名 以及调用注解
 */
public class DumpMethods {
    public static void main(String[] args) {
        testKotlinReflect();
        //methodOverload();
        //findAnnotationMethods();
    }

    private static void testKotlinReflect() {
        RelectClient relectClient = new RelectClient();
        relectClient.invokeKotlinMethod();
    }

    private static void test() {
        //ILoginApi mLogApi = create();
        //mLogApi.login("kingz","123456");
    }


    /**
     * 方法重载测试
     */
    public static void methodOverload() {
        try {
            RelectClient duo = new RelectClient();
            Class<?> c = Class.forName("com.reflect.RelectClient");
            Method method = c.getMethod("numPrint",Integer.TYPE, int.class);
            method.invoke(duo, 111, 222);
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Annotation checker
     */
    public static void findAnnotationMethods() {
        try {
            Class<?> c = Class.forName("com.reflect.RelectClient");
            Method[] mthods = c.getDeclaredMethods();
            for (Method mthod : mthods) {
                //获取函数生声明的注解信息
                //method.getAnnotations()拿到函数注解数组
                // mthod.getDeclaredAnnotations()的区别？？？
                //getParameterAnnotations() 获取参数注解
                Annotation[] declaredAnnotations = mthod.getDeclaredAnnotations();
                //获取指定的注解  method.getAnnotation(ReqType.class);
                System.out.println(mthod.toString());
                for(Annotation annotation:declaredAnnotations){
                    if(annotation instanceof ReqType){
                        System.out.println("这个方法是有ReqType注解 当前方法是：" +  mthod.getName());
                        mthod.invoke(c.newInstance(),new Object[]{"invoke params"});
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
