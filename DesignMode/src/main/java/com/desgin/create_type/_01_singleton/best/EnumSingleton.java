package com.desgin.create_type._01_singleton.best;


/**
 * 在Java中，最好的单例模式写法取决于具体需求
 *
 * 但目前被广泛认可的最佳实践是[枚举单例]和[静态内部类单例] {@link StaticInnerClassSingleton}。
 *
 * [枚举单例]（推荐指数：★★★★★）
 * 优点：
 *     最安全：防止反射攻击和序列化破坏
 *     代码简洁：天然支持序列化
 *     线程安全：由JVM保证
 *     防止多次实例化：枚举的特性保证
 * 缺点：
 *     不支持延迟加载
 *     在某些场景下不够灵活
 *
 * [静态内部类单例] （推荐指数：★★★★☆）
 *
 */
public enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        // 业务逻辑
    }
}
