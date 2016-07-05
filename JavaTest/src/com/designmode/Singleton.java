package com.designmode;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/25 14:13
 * description: 单例模式演变
 */

// version 1.0  懒汉式，线程不安全
public class Singleton {
    private static Singleton singleton = null;
    //私有的构造函数，表明这个类是不可能形成实例了。这主要是怕这个类会有多个实例。
    private Singleton() {}
    //因为这个类不能形成实例，所以需要一个静态方法让其生成实例
    public static Singleton getInstance() {
        if (singleton== null) {
            singleton= new Singleton();
        }
        return singleton;//所形成的实例保存在自己类中的私有成员中
    }
}
/**
 ====================================================================
    上面的这个程序存在比较严重的问题在多线程情况下，如果多个线程同时
 调用getInstance()的话，则可能会有多个进程同时通过 (singleton== null)
 的条件检查于是，多个实例就创建出来。此时就需要线程互斥和同步了。
*/
/**
 version 1.1
 public class Singleton
{
    private static Singleton singleton = null;
    private Singleton() {  }
    public static Singleton getInstance() {
        if (singleton== null) {
            synchronized (Singleton.class) {
                singleton= new Singleton();
            }
        }
        return singleton;
    }
}*/

/**
 ====================================================================
    使用了Java的synchronized方法，看起来不错哦。应该没有问题了吧？！错！
 这还是有问题！为什么呢？前面已经说过，如果有多个线程同时通过(singleton== null)
 的条件检查（因为他们并行运行），虽然我们的synchronized方法会帮助我们同步
 所有的线程，让我们并行线程变成串行的一个一个去new，那不还是一样的吗？
 同样会出现很多实例。嗯，确实如此！看来，还得把那个判断(singleton== null)
 条件也同步起来。
*/
// version 1.2  懒汉式，线程安全
/**
public class Singleton
{
    private static Singleton singleton = null;
    private Singleton()  {  }
    public static Singleton getInstance()  {
        synchronized (Singleton.class) {
            if (singleton== null) {
                  singleton= new Singleton();
            }
         }
        return singleton;
    }
}*/


/**
 ====================================================================
    上面版本同步是一调用就判断一次同步，耗费性能，所以加一层判断。
 这可能是最不错的一个版本了，这个版本又叫“双重检查”Double-Check，是为了提高效率
*/
// version 1.3  双重检验锁，一次是在同步块外，一次是在同步块内
/**
public class Singleton
{
    private static Singleton singleton = null;
    private Singleton()  {    }
    public static Singleton getInstance() {
        if (singleton == null)  {
            synchronized (Singleton.class) {
                if (singleton == null)  {
                    singleton= new Singleton();
                }
            }
        }
        return singleton;
    }
}*/


//===================
//Singleton 的简化版本
//===================
/**
 *====================================================================
 * 这种方法非常简单，单例的实例被声明成 static 和 final 变量了，
 * 在第一次加载类到内存中时就会初始化，所以创建实例本身是线程安全的。
 *
 * 这种玩法的最大问题是——当这个类被加载的时候，new Singleton()
 * 这句话就会被执行，就算是getInstance()没有被调用，类也被初始化了。
 * 因此会占一定的内存。
 */

/**
 version 1.5
 public class Singleton
    {
        private volatile static Singleton singleton = new Singleton();
        private Singleton()  {    }
        public static Singleton getInstance()   {
            return singleton;
        }
 }

 //饿汉式 static final field
   为什么用final 因为被final关键字修饰的类,不能被继承,被final修饰的成员变量不可以被修改.
   此处用了final为了强化和重点突出:"同一对象"这个概念——只有这么一个对象,
   而且它是不可以被修改的.

 public class Singleton{
        private static final Singleton singleton  = new Singleton();
        private Singleton()  {    }
        public static Singleton getInstance()   {
            return singleton;
        }
 }

 public class Singleton{
    private static final Singleton singleton = null;
    static{
      singleton = new Singleton();
    }
    private Singleton()  {   }
    public static Singleton getInstance()   {
        return singleton;
    }
}

*/

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
