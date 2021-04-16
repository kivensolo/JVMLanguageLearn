package com.kingz.kt.deep


/**
 * 演示在构造函数中的危险操作。
 * construtor中的危险操作：
 * 1. 访问在construtor中的非final属性
 * 2. 在construtor中调用非final的函数 (非final函数为可以变函数，初始化的时机会导致情况异常)
 * 3. 在非final类的构造函数中将其用作函数参数
 *
 * 这些操作是危险的，因为类可以继承，而派生类此时尚未初始化。
 * 典型例子如下：
 * 如果调用 println(Derived(42).codeError) ，输出结果为0，与预期的42不一致。
 */
abstract class BaseClass{
    init {
        println("BaseDecoder init")
        //config()
    }

    /**
     * 直接赋值的方式，在字节码层次，会直接写到构造函数中。
     * 对应的字节码代码为：
     *  public BaseDecoder() {
     *    String var1 = "BaseDecoder init";
     *    boolean var2 = false;
     *    System.out.println(var1);
     *    //this.config();
     *    this.codeError = this.config();
     * }
     * 此时子类还没初始化，所以无法进行子类的config处理,
     * 只能得到codeError本身的值。
     */
    val codeError= config()
    /**
     * 父类执行完初始化时, 会调用config(),
     * 但是在子类构造函数初始化完毕后，会对codeRight对象调用get,
     * 会重新获取到42的正确值。
     */
    val codeRight
        get() = config()
    abstract fun config():Int
}


class Derived(private val x: Int) : BaseClass() {
    init {
        println("Derived init")
    }
    override fun config(): Int{
        println("config() current x:$x")
        return x
    }
}

object DangerousTestClient{
    @JvmStatic
    fun main(args: Array<String>) {
        print(Derived(42).codeError)
    }
}