package com.reflect;

import com.annotation.requestDemo.ReqType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * author: King.Z
 * date:  2016/4/24 22:26
 * description:反射测试客户顿
 */
public class RF_TestClient {

    private static final String TEST_CLASS_NAME = "com.reflect.RelectTarget";

    public static void main(String[] args) {
        fieldModify();
        //testKotlinReflect();
        //methodOverload();
        //findAnnotationMethods();
    }

    private static void testKotlinReflect() {
        RelectTarget relectClient = new RelectTarget();
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
            RelectTarget instance = new RelectTarget();
            Class<?> c = Class.forName(TEST_CLASS_NAME);
            Method[] declaredMethods = c.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                String name = declaredMethod.getName();
                System.out.println("declaredMethods +1 ="+name);
            }
            //Method method = c.getMethod("numPrint",Integer.TYPE, int.class);
            //method.invoke(instance, 111, 222);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    /**
     * 内部变量修改:
     * 1. 修改基础数据类型、普通类型的属性;
     * 2. 修改final修饰的属性，包括基础数据类型和自定义封装类型;
     *    只要不被编译器内联优化的 final 属性都可以通过反射有效的进行修改(修改后能使用到新的值)
     *    但如果 final 属性值是通过构造函数传入，那就可以，因为它不会被编译器内联优化。
     */
    public static void fieldModify() {
        try {
            RelectTarget instance = new RelectTarget();

            Class<?> client = Class.forName(TEST_CLASS_NAME);

            // <editor-fold defaultstate="collapsed" desc="非final的基础变量修改">
            Field numFiled = client.getDeclaredField("num");
            numFiled.setAccessible(true);
            // 从client中获取num数据对象，是Integer的
            Object numObj = numFiled.get(instance);
            System.out.println("Pre reflect num="+numObj);
            numFiled.set(instance,10086);

            Field strFiled = client.getDeclaredField("str");
            strFiled.setAccessible(true);
            String strObj = (String)strFiled.get(instance);
            System.out.println("Pre reflect str="+ strFiled.getModifiers() + ";" +strObj);
            strFiled.set(instance,"10086");
            // </editor-fold>

           // <editor-fold defaultstate="collapsed" desc="final变量修改">
            // 用于修改修饰符  去除final
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);  //Field 的 modifiers 是私有的

            // 尝试修改基础类型的final对象
            Field debugFiled = client.getDeclaredField("DEBUG_MODE");
            debugFiled.setAccessible(true);
            boolean booleanObj = (boolean)debugFiled.get(instance);
            System.out.println("Modify final DEBUG_MODE(Pre) ="+booleanObj + "; 修饰符=" + debugFiled.getModifiers());
            modifiersField.setInt(debugFiled, debugFiled.getModifiers() & ~Modifier.FINAL);
            System.out.println("Modify final DEBUG_MODE(After) Modifiers = " + debugFiled.getModifiers());
            debugFiled.setBoolean(instance, true); // 修改后输出还是原来的值，因为会对基本类型的final值进行内联编译
            boolean newFinalBoolean = (boolean)debugFiled.get(instance);
            System.out.println("Dump DEBUG_MODE(After modify) = " + newFinalBoolean);


            // 非基础类型的final对象值可以修改
            Field finalCatFiled = client.getDeclaredField("finalCat");
            finalCatFiled.setAccessible(true);
            modifiersField.setInt(finalCatFiled, finalCatFiled.getModifiers() & ~Modifier.FINAL);
            finalCatFiled.set(instance, null);

            /*
             * 只要不被编译器内联优化的 final 属性就可以通过反射有效的进行修改
             */
            // </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="修改集合对象">
            Field field = client.getDeclaredField("innerList");
            field.setAccessible(true);
            field.set(instance,null);
            System.out.println("After reflect :" + instance);
            // </editor-fold>
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
