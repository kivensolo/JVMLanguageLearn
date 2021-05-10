package com.desgin.create_type._02_factory_design.abstract_factory;

/**
 * 具体工厂
 * 负责具体得披萨产品生产
 *
 * 实现了具体工厂可创建多类产品的功能。
 */
public class PizzaFactory extends AbsFactory {
    @Override
    CommonPizza createChinesePizza() {
        return new ChinesePizza();
    }

    @Override
    CommonPizza createHotPizza() {
        return new HotPizza();
    }
}
