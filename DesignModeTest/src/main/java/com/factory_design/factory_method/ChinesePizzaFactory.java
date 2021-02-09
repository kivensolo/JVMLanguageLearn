package com.factory_design.factory_method;


import com.factory_design.abstract_factory.CommonPizza;
import com.factory_design.products.ChinesePizza;

/**
 * author: King.Z <br>
 * date:  2017/3/31 21:24 <br>
 * description: 具体工厂实现类
 */
public class ChinesePizzaFactory extends BasePizzaFactory {
    public CommonPizza createPizza(String type) {
        return new ChinesePizza();
    }
}
