package com.adapter.objectAdapter;

import com.adapter.classadapter.ITargetable;
import com.adapter.classadapter.Source;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/8/24 14:11 <br>
 * description: 对象的适配器模式使用
 *   基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，
 *   而是持有Source类的实例，以达到解决兼容性的问题<br>
 *  应用场景：当希望将一个对象（Source）转换成满足另一个新接口的对象时，
 *  可以创建一个Wrapper类，持有原类的一个实例，
 *  在Wrapper类的方法中，调用实例的方法就行。
 */
public class Wrapper implements ITargetable{

    private Source source;

    public Wrapper (Source source) {
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
