package com.desgin.create_type._02_factory_design.factory_method;

import com.desgin.create_type._02_factory_design.abstract_factory.CommonPizza;

/**
 * author: King.Z <br>
 * date:  2017/3/31 21:10 <br>
 * description: 工厂方法模式之抽象工厂层<br>
 */
public abstract class BasePizzaFactory {

    public CommonPizza orderPizza(String type){
        CommonPizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    public abstract CommonPizza createPizza(String type);
}
