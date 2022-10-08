package com.desgin.create_type._02_factory_design.sample01.abstractfactory;

import com.desgin.create_type._02_factory_design.sample01.abstractfactory.products.drinks.Cola;
import com.desgin.create_type._02_factory_design.sample01.abstractfactory.products.snacks.ChocolateShack;

/**
 * author: King.Z
 * date:  2016/1/6 14:46
 * description:顾客B点了：可乐 + 巧克力奶昔
 */
public class Test_B {
     public static void main(String[] args) {
        Order order = new Order.OrderBuilder()
                .addDrinks(new Cola())
                .addSnack(new ChocolateShack())
                .build();
        System.out.println("Test_B ：" + order.makeOrder());
    }
}
