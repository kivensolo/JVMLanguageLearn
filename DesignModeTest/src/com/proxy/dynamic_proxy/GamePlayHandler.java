package com.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler是JDK提供的动态代理接口，对被代理类的方法进行代理
 */
public class GamePlayHandler implements InvocationHandler {
    //被代理者
    Class cls =null;
    //被代理对象
    Object target = null;

    //要代理谁
    public GamePlayHandler(Object _obj){
        this.target = _obj;
    }

     //代理方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equalsIgnoreCase("login")){
            System.out.println("---->有人在用账号登陆<----");
        }
        return method.invoke(this.target, args);
    }
}
