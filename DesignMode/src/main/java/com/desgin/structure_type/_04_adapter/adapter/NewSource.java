package com.desgin.structure_type._04_adapter.adapter;

/**
 * author: King.Z <br>
 * date:  2016/8/24 14:11 <br>
 * description: 类适配器模式使用 <br>
 *  应用场景:
 *      当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，
 *      创建一个新类，继承原有的类，实现新的接口即可
 *
 *  用于扩展和迭代的时候使用
 */
public class NewSource extends Source implements ITargetable{
    @Override
    public void method1() {
        super.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
