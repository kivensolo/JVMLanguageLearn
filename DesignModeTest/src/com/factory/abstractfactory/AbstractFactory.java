package com.factory.abstractfactory;

import com.factory.Sample;
import com.factory.Sample2;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/6/29 0:32 <br>
 * description: 就将Factory变成抽象类,将共同部分封装在抽象类中,不同部分使用子类实现
 */
public abstract class AbstractFactory {
    public abstract Sample creator();
    public abstract Sample2 creator(String name);
}
class SimpleFactory extends AbstractFactory{

    @Override
    public Sample creator() {
        System.out.println("SimpleFactory ----- Create Sample");
        return new Sample();
    }

    @Override
    public Sample2 creator(String name) {
        System.out.println("SimpleFactory ----- Create Sample2");
        return new Sample2();
    }
}
