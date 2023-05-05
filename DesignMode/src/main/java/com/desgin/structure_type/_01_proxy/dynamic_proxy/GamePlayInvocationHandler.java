package com.desgin.structure_type._01_proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler是JDK提供的动态代理接口，对被代理类的方法进行代理
 */
public class GamePlayInvocationHandler implements InvocationHandler {
    //被代理者
    Class cls =null;
    //被代理对象
    Object target = null;

    //指定代理对象
    public GamePlayInvocationHandler(Object _obj){
        this.target = _obj;
    }

     //调用被代理的方法   它完成对正式方法的调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke() ---->" + "Method = " + method.getName());
        if(method.getName().equalsIgnoreCase("login")){
            System.out.println("---->有人在用账号登陆<----");
        }
        return method.invoke(this.target, args);
    }
}
