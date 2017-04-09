package com.factory_design.stroe;

import com.factory_design.pizzas.Pizza;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/3/31 21:10 <br>
 * description: 原始披萨店 <br>
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    //让每个加盟店有自己的制作披萨方法
    public abstract Pizza createPizza(String type);
}
