package com.desgin.create_type._02_factory_design.abstract_factory;

/**
 * author: King.Z <br>
 * date:  2017/3/31 21:01 <br>
 * description: 抽象产品层 <br>
 */
public abstract class CommonPizza {
    public abstract void prepare();

    public abstract void bake();

    public abstract void cut();

    public abstract void box();
}
