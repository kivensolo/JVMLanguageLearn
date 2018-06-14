package com.factory_design.stroe;


import com.factory_design.pizzas.ChinesePizza;
import com.factory_design.pizzas.Pizza;

/**
 * author: King.Z <br>
 * date:  2017/3/31 21:24 <br>
 * description: 加盟店之一 ------- 中国披萨店 <br>
 *     创造属于中国口味的披萨
 */
public class ChinesePizzaStore extends PizzaStore{
    public Pizza createPizza(String type) {
        Pizza pizza = new ChinesePizza();
        return pizza;
    }
}
