package com.desgin.structure_type._01_proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ※ 动态代理在实现阶段不用关心代理谁，而是在运行阶段才指定代理谁。
 * ※ 之前的两种代理方式，需要自己写代理类的方式就是静态代理。
 *
 * AOP编程思想
 */
public class Test {
    public static void main(String[] args) throws Throwable {
        IGamePlayer player = new GamePlayer("张三");
        // 传入具体对象到Handler中，invoke方法回调时，反射调用具体对象的方法。
        InvocationHandler handler = new GamePlayHandler(player);
        System.out.println("开始时间是：2017-6-24 10:45");
        // 获取到具体对象类的ClassLoader
        ClassLoader cl = player.getClass().getClassLoader();
        //// 获取具体对象抽象接口的字节码代理对象
        //Class clazz = Proxy.getProxyClass(cl, IGamePlayer.class);
        //// 通过具体对象抽象接口的字节码对象，传入一个InvocationHandler对象，
        //// 实例化一个需要代理的对象接口
        //// 这样对具体对象那个的方法调用，就会触发handler的invoke()回调方法
        //IGamePlayer proxy = (IGamePlayer)clazz.getConstructor(InvocationHandler.class).newInstance(handler);
        //简洁调用
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(
                cl,  // 被代理对象类的classLoader实例
                new Class[] { IGamePlayer.class }, // 被代理对象类的实现接口字节码对象
                handler); // InvocationHandler通知对象
        proxy.login("张三","123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是：2017-6-25 03:40");
    }
}
