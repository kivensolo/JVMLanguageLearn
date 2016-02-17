package com.hello.kevin;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/15 16:01
 * description:
 *      观察初始化字符串whenAmISet最后被打印的值
 *   -----------------> 流程
 *     1、 进入Derived 构造函数。
       2、 Derived 成员变量的内存被分配。
       3、Base 构造函数被隐含调用。
       4、Base 构造函数调用preProcess()。
       5、Derived 的preProcess 设置whenAmISet 值为 “set in preProcess()”。
       6、Derived 的成员变量初始化被调用。
       7、执行Derived 构造函数体。
     <-----------------
 等一等，这怎么可能？在第6步，Derived 成员的初始化居然在 preProcess() 调用之后？
 是的，正是这样，我们不能让成员变量的声明和初始化变成一个原子操作，
 虽然在Java中我们可以把其写在一起，让其看上去像是声明和初始化一体。
 但这只是假象，我们的错误就在于我们把Java中的声明和初始化看成了一体。
 在C++的世界中，C++并不支持成员变量在声明的时候进行初始化，
 其需要你在构造函数中显式的初始化其成员变量的值，看起来很土，但其实C++用心良苦。
 */
public class Base
{
    Base() {
        preProcess();
    }

    void preProcess() {}
}
class Derived extends Base
{
    public String whenAmISet = "set when declared";

    @Override void preProcess()
    {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("方法名 == " + methodName);
        whenAmISet = "set in preProcess()";
    }
}
