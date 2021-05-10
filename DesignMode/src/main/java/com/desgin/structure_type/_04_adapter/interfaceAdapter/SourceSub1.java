package com.desgin.structure_type._04_adapter.interfaceAdapter;

/**
 * author: King.Z <br>
 * date:  2016/8/24 14:32 <br>
 * description: 指向具体实现摸一个方法的类 <br>
 *  应用场景：
 *  当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，
 *  实现所有方法，我们写别的类的时候，继承抽象类即可
 */
public class SourceSub1 extends SourceWrapper {
    @Override
    public void method1() {
        System.out.println("the sourceable interface's first Sub1!");
    }
}
