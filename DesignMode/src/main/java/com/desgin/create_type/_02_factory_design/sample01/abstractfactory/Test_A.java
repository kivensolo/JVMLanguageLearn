package com.desgin.create_type._02_factory_design.sample01.abstractfactory;

import com.desgin.create_type._02_factory_design.sample01.abstractfactory.products.drinks.OrangeJuice;
import com.desgin.create_type._02_factory_design.sample01.abstractfactory.products.hamburgers.CheckenBurgur;
import com.desgin.create_type._02_factory_design.sample01.abstractfactory.products.snacks.ApplePie;

/**
 * author: King.Z
 * date:  2016/1/6 14:32
 * description: 顾客A点了：鸡肉汉堡 + 橙汁 + 苹果派
 */
public class Test_A {
    public static void main(String[] args) {
        Order order = new Order.OrderBuilder()
                .addBurger(new CheckenBurgur())
                .addDrinks(new OrangeJuice())
                .addSnack(new ApplePie())
                .build();
        System.out.println("Test_A ：" + order.makeOrder());
    }
}
