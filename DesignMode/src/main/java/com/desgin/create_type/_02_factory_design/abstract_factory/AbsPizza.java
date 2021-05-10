package com.desgin.create_type._02_factory_design.abstract_factory;

/**
 * 抽象披萨产品
 */
public abstract class AbsPizza extends CommonPizza {
    @Override
    public abstract void prepare();

    @Override
    public abstract void bake();

    @Override
    public abstract void cut();

    @Override
    public abstract void  box();
}
