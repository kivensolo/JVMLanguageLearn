package com.factory_design;

import factory_design.pizzas.ChinesePizza;
import factory_design.pizzas.HotPizza;
import factory_design.pizzas.Pizza;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/3/31 21:05 <br>
 * description: XXXXXXX <br>
 */
public class SimplePizzaFactory {
    //职责 创建各种pizza
    public Pizza createPizza(String type){
        Pizza pizza = null;
        if(type.equals("Cheese")){
            pizza = new ChinesePizza();
        }else if(type.equals("Hotdog")){
            pizza = new HotPizza();
        }
        return pizza;
    }
}
