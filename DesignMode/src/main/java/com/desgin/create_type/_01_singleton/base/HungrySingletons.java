package com.desgin.create_type._01_singleton.base;

public class HungrySingletons {

    /**
     * 基础饿汉式
     *
     * 单例的实例被声明成 static 和 final 变量，类初次被加载时，就会创建实例，保证了线程安全。
     * 但这也是它的最大问题所在，会占一定的内存，无法实现懒加载机制。
     *
     * 此处用了final为了强化和重点突出:"同一对象"这个概念——只有这么一个对象。
     */
    public static class HungryBaseSingleton{
        private static final HungryBaseSingleton singleton = new HungryBaseSingleton();
        private HungryBaseSingleton()  {    }
        public static HungryBaseSingleton getInstance()   {
            return singleton;
        }
    }

    /**
     * 静态代码块饿汉式单例
     * 优点：
     *      同样线程安全
     *      可以在静态代码块中执行更复杂的初始化逻辑
     *      类加载时创建实例，获取实例性能好
     * 缺点：
     *      同样存在资源可能浪费的问题
     *      没有懒加载机制
     *      代码稍微复杂一些。
     */
    public static class HungryStaticSingleton{
        private static HungryStaticSingleton singleton = null;
        static{
            singleton = new HungryStaticSingleton();
        }
        private HungryStaticSingleton()  {   }
        public static HungryStaticSingleton getInstance()   {
            return singleton;
        }
    }
}
