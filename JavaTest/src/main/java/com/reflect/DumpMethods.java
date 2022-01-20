package com.reflect;

import com.annotation.requestDemo.ReqType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author: King.Z
 * date:  2016/4/24 22:26
 * description:反射:获取类的方法名 以及调用注解
 */
public class DumpMethods {

    private static final String TEST_CLASS_NAME = "com.reflect.RelectClient";

    public static void main(String[] args) {
        fieldModify();
        //testKotlinReflect();
        methodOverload();
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
            Class<?> c = Class.forName(TEST_CLASS_NAME);
            Method[] declaredMethods = c.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                String name = declaredMethod.getName();
                System.out.println("declaredMethods +1 ="+name);
            }
            //Method method = c.getMethod("numPrint",Integer.TYPE, int.class);
            //method.invoke(duo, 111, 222);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    /**
     * 内部变量修改
     * ArrayList
     */
    public static void fieldModify() {
        try {
            RelectClient duo = new RelectClient();
            Class<?> client = Class.forName(TEST_CLASS_NAME);
            Field numFiled = client.getDeclaredField("num");
            numFiled.setAccessible(true);
            numFiled.set(duo,777);
            Field field = client.getDeclaredField("innerList");
            field.setAccessible(true);
            field.set(duo,null);
            System.out.println("Change Filed:" + duo.toString());

            //field.get()
            //field.setAccessible(true);
            //field.set()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Annotation checker
     */
    public static void findAnnotationMethods() {
        try {
            Class<?> c = Class.forName(TEST_CLASS_NAME);
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
