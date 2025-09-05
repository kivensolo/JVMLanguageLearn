package com.desgin.structure_type._03_decorator;

/**
 * author: King.Z <br>
 * date:  2016/8/25 17:01 <br>
 * updateDate: 2025/09/05 <br>
 * description:
 * 【目的】:  装饰模式是给一个对象增强和添加功能，而且是动态的，
 * 【接口一致性】  [装饰对象]和[被装饰对象]实现同一个接口，
 * 【生命周期】 与被装饰对象生命周期无关
 * 【新增功能】 扩展原对象的功能
 *  当前样例中，{@link Source}是被装饰类，{@link Decorator}是一个装饰类，
 *  在装饰类中，可以持有N个被装饰类对象的实例，在执行某个流程方法的时候，
 *  去调用被装饰类对象中的同名方法即可。
 *
 *  其实乍一看，装饰者模式和代理模式在结构上很相似，但它们的设计意图和应用场景有明显区别。
 *  装饰者模式主要目的是动态地给对象“添加新的功能”， 但代理模式的目的是“控制访问”
 *  并且装饰者模式的生命周期和被装饰者无关，但是代理模式就有关。
 */
public class Decorator implements Sourceable{

    private Sourceable sourceable;

    public Decorator(Sourceable sourceable) {
        super();
        this.sourceable = sourceable;
    }

    @Override
    public void doSomething() {
        System.out.println("before decorator!");
        sourceable.doSomething();
        System.out.println("after decorator!");
    }
}
