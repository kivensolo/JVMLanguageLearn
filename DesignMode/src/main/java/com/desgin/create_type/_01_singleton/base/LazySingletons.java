package com.desgin.create_type._01_singleton.base;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/25 14:13
 * description: 单例模式的演变
 *
 * 为了方便演示，把每个效果变成了内部类，所以加static修饰。实际使用通常不会对类做静态修饰。
 */

public class LazySingletons {

    /*
     * 懒汉式 v1.0 低级版
     *
     * 【线程不安全】
     *  在多线程情况下，如果多个线程同时调用getInstance()的话，
     *  则可能会有多个进程同时通过 (singleton== null)的条件检查于是，
     *  多个实例就创建出来。此时就需要线程互斥和同步了。
     */
    public static class SingletonBase {
        private static SingletonBase singleton = null;
        //私有的构造函数，表明这个类是不可能形成实例了。这主要是怕这个类会有多个实例。
        private SingletonBase() {}
        //因为这个类不能形成实例，所以需要一个静态方法让其生成实例
        public static SingletonBase getInstance() {
            if (singleton== null) {
                singleton= new SingletonBase();
            }
            return singleton;//所形成的实例保存在自己类中的私有成员中
        }
    }

    /*
     * 懒汉式 v1.1 同步锁版本
     *
     * 线程安全, 但耗费性能
     */
    public static class SingletonSyncBase{
        private static SingletonSyncBase singleton = null;
        private SingletonSyncBase() {  }
        public static SingletonSyncBase getInstance() {
            synchronized (SingletonSyncBase.class) {
                if (singleton== null) {
                    singleton= new SingletonSyncBase();
                }
            }
            return singleton;
        }
    }

    /**
     * “双重检查”
     *
     * 仅仅使用同步锁, 每调用一次就判断一次同步，耗费性能，所以加一层判断,实现双重检查。
     *
     * 这可能是最不错的一个版本了，是为了提高效率。
     * 优点：
     *      延迟加载：只有在需要时才创建实例
     *      线程安全：通过双重检查锁保证
     *      性能较好：只在第一次创建实例时加锁
     * 缺点：
     *      实现相对复杂
     *      需要正确使用 volatile 关键字
     *
     * Android Glide 中 get方法获取实例就是通过这种方式。
     */
    public static class DoubleCheckSingleton {
        // volatile保证可见性和禁止指令重排序
        private static volatile DoubleCheckSingleton singleton = null;
        private DoubleCheckSingleton()  {}
        public static DoubleCheckSingleton getInstance() {
            if (singleton == null)  {
                synchronized (DoubleCheckSingleton.class) {
                    if (singleton == null)  {
                        singleton= new DoubleCheckSingleton();
                    }
                }
            }
            return singleton;
        }
    }


}


/**
 * ====================================================================
    于是，这个可能会与我们想要的行为不一样，比如，我的类的构造函数中，
 有一些事可能需要依赖于别的类干的一些事（比如某个配置文件，或是某个被其它类
 创建的资源），我们希望他能在我第一次getInstance()时才被真正的创建。这样，
 我们可以控制真正的类创建的时刻，而不是把类的创建委托给了类装载器。
 下面的这个1.6版是老版《Effective Java》中推荐的方式。
 */

/**
  version 1.6  静态内部类 static nested class【很好的方法】
  public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton (){}
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}*/

//上面这种方式，仍然使用JVM本身机制保证了线程安全问题；
// 由于 SingletonHolder 是私有的，除了 getInstance()
// 之外没有办法访问它，因此它只有在getInstance()被调用
// 时才会真正创建；同时读取实例的时候不会进行同步，
// 没有性能缺陷；也不依赖 JDK 版本。
