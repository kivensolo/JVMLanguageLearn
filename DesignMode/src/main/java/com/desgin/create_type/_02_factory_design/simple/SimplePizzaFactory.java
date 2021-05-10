package com.desgin.create_type._02_factory_design.simple;

import com.desgin.create_type._02_factory_design.abstract_factory.CommonPizza;
import com.desgin.create_type._02_factory_design.products.ChinesePizza;
import com.desgin.create_type._02_factory_design.products.HotPizza;

/**
 * author: King.Z <br>
 * date:  2017/3/31 21:05 <br>
 * description: 简单工厂 <br>
 * 工厂模式按照《Java与模式》中的提法分为三类：
 * 1. 简单(静态)工厂模式(Simple Factory)
 * 2. 工厂方法模式(Factory Method)
 * 3. 抽象工厂模式(Abstract Factory)
 * 这三种模式从上到下逐步抽象，并且更具一般性。
 *
 * 简单(静态)工厂模式总结：
 * 特点
 * 1 它是一个具体的类，非接口、抽象类。
 *   有一个重要的create()方法，利用if或者switch创建产品并返回。
 * 2 create()方法通常是静态的，所以也称之为静态工厂。
 *
 * 缺点
 * 1 违背“开放 - 关闭原则”，扩展性差（一旦添加新产品就不得不修改工厂类的逻辑）
 * 2 不同的产品需要不同额外参数的时候 不支持(哪怕使用Object参数去兼容)。
 */
public class SimplePizzaFactory {
    //职责 创建各种pizza
    public static CommonPizza createPizza(String type){
        CommonPizza pizza = null;
        if(type.equals("Cheese")){
            pizza = new ChinesePizza();
        }else if(type.equals("Hotdog")){
            pizza = new HotPizza();
        }
        return pizza;
    }
}
