package com.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理类是一个实现在创建类时在运行时指定的接口列表的类，该类具有下面描述的行为。
 * 【代理接口】 是代理类实现的一个接口。
 * 【代理实例】 是代理类的一个实例。
 * 每个代理实例都有一个关联的调用处理程序对象，它可以实现接口 InvocationHandler。
 * 通过其中一个代理接口的代理实例上的方法调用将被指派到实例的调用处理程序的 Invoke 方法，
 * 并传递代理实例、识别调用方法的 java.lang.reflect.Method 对象以及包含参数的 Object 类型的数组。
 * 调用处理程序以适当的方式处理编码的方法调用，并且它返回的结果将作为代理实例上方法调用的结果返回。

 */
public class Client {
    public static void main(String[] args) throws Throwable {
        //定义一个痴迷的玩家
        IGamePlayer player = new GamePlayer("张三");
        //定义一个handler  The invocation handler to dispatch method invocations to
        InvocationHandler handler = new GamePlayHandler(player);
        //记下时间戳
        System.out.println("开始时间是：2017-6-24 10:45");
        //The class loader to define the proxy class
        ClassLoader cl = player.getClass().getClassLoader();
        //动态产生一个IGamePlayer的代理
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl,new Class[] { IGamePlayer.class },handler);
        //等同于：
        //Class proxyClass = Proxy.getProxyClass(cl, new Class[] { IGamePlayer.class });
        //IGamePlayer proxy = (IGamePlayer)proxyClass.getConstructor(new Class[]{InvocationHandler.class}).newInstance(new Object[]{handler});


        //登录
        proxy.login("zhangsan","123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是：2017-6-25 03:40");
    }
}
