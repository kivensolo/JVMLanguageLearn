package com.desgin.create_type._02_factory_design.factory_method;


import com.desgin.create_type._02_factory_design.abstract_factory.CommonPizza;
import com.desgin.create_type._02_factory_design.products.HotPizza;

/**
 * author: King.Z <br>
 * date:  2017/3/31 21:25 <br>
 * description: 具体工厂实现类 <br>
 *     每一个具体工厂只能创建一种产品实例
 */
public class ChicagoPizzaFactory extends BasePizzaFactory {
    public CommonPizza createPizza(String type) {
        return new HotPizza();
    }
}
