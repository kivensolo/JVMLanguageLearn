package com.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ※ 动态代理在实现阶段不用关心代理谁，而是在运行阶段才指定代理谁。
 * ※  之前的两种代理方式，需要自己写代理类的方式就是静态代理。
 *
 * AOP编程思想
 */
public class Client {
    public static void main(String[] args) throws Throwable {
        IGamePlayer player = new GamePlayer("张三");
        InvocationHandler handler = new GamePlayHandler(player); //传入具体对象，方法回调时，反射调用其方法。
        System.out.println("开始时间是：2017-6-24 10:45");
        //获取到具体对象类的ClassLoader
        ClassLoader cl = player.getClass().getClassLoader();
        //通过反射动态产生代理
        Class clazz = Proxy.getProxyClass(cl, IGamePlayer.class); //获取到需要代理的Foo接口的class
        //通过Foo接口的class，传入一个InvocationHandler对象，实例化一个需要代理的对象接口
        // 这样对具体对象那个的方法调用，就会触发handler的invoke()回调方法
        IGamePlayer proxy = (IGamePlayer)clazz.getConstructor(InvocationHandler.class).newInstance(handler);
        //简洁调用
        //IGamePlayer proxy2 = (IGamePlayer) Proxy.newProxyInstance(cl,new Class[] { IGamePlayer.class },handler);
        proxy.login("张三","123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是：2017-6-25 03:40");
    }
}
