package com.desgin.create_type._01_singleton.best;

/**
 * 静态内部类单例（推荐指数：★★★★☆）
 *
 * 优点：
 *      线程安全：由JVM类加载机制保证
 *      延迟加载：只有在调用 getInstance() 时才会加载内部类并创建实例
 *      性能优秀：避免了同步带来的性能开销
 *      防止反射攻击：相对安全（但仍可能被反射破坏）
 * 缺点：
 *      仍可能被反射破坏
 *      序列化需要额外处理
 *
 * 还有双重检查锁单例（推荐指数：★★★★）
 * {@link  com.desgin.create_type._01_singleton.base.LazySingletons.DoubleCheckSingleton}
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {}

    // 静态内部类
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
