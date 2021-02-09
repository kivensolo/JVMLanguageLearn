package com.reflect;

import com.annotation.requestDemo.ReqType;
import com.annotation.requestDemo.ReqTypeEnum;

import java.lang.reflect.Method;

/**
 * date:  2016/9/4 17:42
 * description:反射测试
 */
public class RelectClient {
    private int num = 555;
    private String str = "hello";

    //没有这个无参构造函数 会导致class.newInstance的时候抛出InstantiationException异常
    //在Class的getConstructor0方法中会抛出异常
    public RelectClient(){

    }
    public RelectClient(int num, String str) {
        this.num = num;
        this.str = str;
    }
    public String getDumpStr(){
        return str;
    }

    private int getDumpNum(){
        return num;
    }

    public void numPrint(int num1){
        System.out.println("num1:" + num1);
    }
    public void numPrint(int num1,int num2){
        System.out.println("num1:" + num1 + "num2:" + num2);
    }

    @ReqType({ReqTypeEnum.POST})
    public void findRequestType(String name){
        System.out.println(" ----- findRequestType() has been invoked!!");
        System.out.println(" ----- name:" + name);
    }

    /**
     * Kotlin静态反射方法测试
     */
    void invokeKotlinMethod() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Class<?> classObj = classLoader.loadClass("com.reflect.KtTarget");
            if(KtTarget.class.isAssignableFrom(classObj)){
                Method method = classObj.getMethod("registerStatic");
                method.setAccessible(true);
                method.invoke(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
