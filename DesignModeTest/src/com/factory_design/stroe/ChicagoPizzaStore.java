package com.factory_design.stroe;


import com.factory_design.pizzas.HotPizza;
import com.factory_design.pizzas.Pizza;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/3/31 21:25 <br>
 * description: XXXXXXX <br>
 */
public class ChicagoPizzaStore extends PizzaStore {
    public Pizza createPizza(String type) {
        Pizza pizza = new HotPizza();
        return pizza;
    }
}
