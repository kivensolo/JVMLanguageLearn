package com.reflect;

import com.annotation.requestDemo.ReqType;
import com.annotation.requestDemo.ReqTypeEnum;

/**
 * date:  2016/9/4 17:42
 * description:反射测试
 */
public class Dump {
    private int num = 555;
    private String str = "hello";

    //没有这个无参构造函数 会导致class.newInstance的时候抛出InstantiationException异常
    //在Class的getConstructor0方法中会抛出异常
    public Dump(){

    }
    public Dump(int num, String str) {
        this.num = num;
        this.str = str;
    }
    public String getDumpStr(){
        return str;
    }

    private int getDumpNum(){
        return num;
    }

    @ReqType({ReqTypeEnum.POST})
    public void findRequestType(String name){
        System.out.println(" ----- findRequestType() has been invoked!!");
        System.out.println(" ----- name:" + name);
    }
}
