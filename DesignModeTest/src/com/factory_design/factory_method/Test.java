package com.factory_design.factory_method;

import com.factory_design.abstract_factory.CommonPizza;

public class Test {
    public static void main(String[] args) {
        //创建具体工厂
        ChinesePizzaFactory chinesePF = new ChinesePizzaFactory();
        ChicagoPizzaFactory chicagoPF = new ChicagoPizzaFactory();
        //创建具体产品
        CommonPizza pizza = chinesePF.createPizza("");
        pizza.prepare();
        CommonPizza pizzaM = chicagoPF.createPizza("");
        pizzaM.prepare();
    }
}
