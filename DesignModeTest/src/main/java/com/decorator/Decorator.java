package com.decorator;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/8/25 17:01 <br>
 * description: 装饰模式就是给一个对象增加一些新的功能，而且是动态的，
 *   要求[装饰对象]和[被装饰对象]实现同一个接口，[装饰对象]持有[被装饰对象]的实例 <br>
 *   Source类是被装饰类，Decorator类是一个装饰类，可以为Source类动态的添加一些功能
 */
public class Decorator implements Sourceable{

    private Sourceable sourceable;

    public Decorator(Sourceable sourceable) {
        super();
        this.sourceable = sourceable;
    }

    @Override
    public void method() {
        System.out.println("before decorator!");
        sourceable.method();
        System.out.println("after decorator!");
    }
}
