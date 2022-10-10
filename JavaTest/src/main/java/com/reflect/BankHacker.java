package com.reflect;

import com.reflect.demo.ICBCBank;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class BankHacker {
    public static void main(String[] args) {
        //hackABCBankField();
        getAnnotationInfo();
    }

    /**
     * "入侵ICBC银行的字段"
     * 内部变量修改:
     * 1. 修改基础数据类型、普通类型的属性;
     * 2. 修改final修饰的属性，包括基础数据类型和自定义封装类型;
     *    只要不被编译器内联优化的 final 属性都可以通过反射有效的进行修改(修改后能使用到新的值)
     *    但如果 final 属性值是通过构造函数传入，那就可以，因为它不会被编译器内联优化。
     */
    private static void hackABCBankField(){
        try {
            Class<?> bankclass = ICBCBank.class;

            ICBCBank instance = (ICBCBank) bankclass.newInstance();

        // <editor-fold defaultstate="collapsed" desc="非final的基础变量修改">
            Field numFiled = bankclass.getDeclaredField("accountMoney");
            numFiled.setAccessible(true);
            Object numObj = numFiled.get(instance); //Integer的
            System.out.println("accountMoney type="+numObj);
            numFiled.set(instance, 1000000);

            Field name = bankclass.getDeclaredField("accoutnName");
            name.setAccessible(true);
            String strObj = (String)name.get(instance);
            System.out.println("accoutnName type="+ name.getModifiers() + ";" +strObj);
            name.set(instance,"百万富翁");
            System.out.println(instance);
        // </editor-fold>

            System.out.println();
            System.out.println("Final 类型的Filed修改:");
        // <editor-fold defaultstate="collapsed" desc="final变量修改">
            // 获取修饰符字段，用于去除final
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);

            // 修改基础类型的final对象值 (将国籍修改为韩国)
            Field finalCatFiled = bankclass.getDeclaredField("country");
            finalCatFiled.setAccessible(true);
            modifiersField.setInt(finalCatFiled, finalCatFiled.getModifiers() & ~Modifier.FINAL);
            finalCatFiled.set(instance, "Korea");
            String coun = (String)finalCatFiled.get(instance);
            System.out.println("修改后的country Filed：" + coun);

            //(改为visa卡)
            Field primitiveFinalFiled = bankclass.getDeclaredField("isVisa");
            primitiveFinalFiled.setAccessible(true);
            boolean bool = (boolean)primitiveFinalFiled.get(instance);
            System.out.println("Filed(final boolean) value:"+bool + "; Modifiers=" + primitiveFinalFiled.getModifiers());

            modifiersField.setInt(primitiveFinalFiled, primitiveFinalFiled.getModifiers() & ~Modifier.FINAL);
            System.out.println("Modify Filed(erase final) Modifiers = " + primitiveFinalFiled.getModifiers());
            primitiveFinalFiled.setBoolean(instance, true);
            //NOTE:: 修改后发现输出还是原来的值，因为JVM会对基本类型的final值进行内联编译
            boolean newFinalBoolean = (boolean)primitiveFinalFiled.get(instance);
            System.out.println("Dump Filed(After modify) value= " + newFinalBoolean);
            System.out.println(instance);
            System.out.println("【结论】: 实例对象自身被final修饰的基础类型数据字段，无法通过反射有效的进行修改, 因为已经被编译器内联优化。");
            //TODO 但为什么Filed的get已经正确了？却没有同步到实例对象里面去
        // </editor-fold>
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * "入侵ICBC银行的所有方法中注解的信息，包含方法注解和参数注解"
     * 获取方法的注解或者参数的注解
     */
    private static void getAnnotationInfo() {
        Class<?> bankclass = ICBCBank.class;
        Method[] declaredMethods = bankclass.getDeclaredMethods();
        System.out.println("====方法的相关注解检测====");
        for (Method method : declaredMethods) {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            if(declaredAnnotations.length > 0){
                StringBuilder annotationNames = new StringBuilder();
                for (Annotation annotation : declaredAnnotations) {
                    annotationNames.append(annotation.annotationType().getName()).append(" ");
                }
                System.out.printf("方法[%s]上声明的注解有：%s%n", method.getName(),annotationNames);
            }else{
                System.out.printf("方法[%s]上没有声明的注解%n",method.getName());
            }
            //一位数组表示有几个参数，二位数据表示每一个参数有多少个注解
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) { //遍历每一个参数
                int annotationsLength = parameterAnnotations[i].length;
                if (annotationsLength > 0) {
                    StringBuilder paramsAnno = new StringBuilder();
                    for (Annotation annotation : parameterAnnotations[i]) {
                        paramsAnno.append(annotation.getClass().getName()).append(" ");
                    }
                    System.out.printf("    arg%d存在注解:%s  %n",i, paramsAnno);
                } else {
                    System.out.printf("    arg%d没有声明参数注解. %n", i);

                }
            }
        }
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
}
