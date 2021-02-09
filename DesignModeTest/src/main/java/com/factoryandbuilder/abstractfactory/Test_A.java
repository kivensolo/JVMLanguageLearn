package com.factoryandbuilder.abstractfactory;

import com.factoryandbuilder.abstractfactory.products.drinks.OrangeJuice;
import com.factoryandbuilder.abstractfactory.products.hamburgers.CheckenBurgur;
import com.factoryandbuilder.abstractfactory.products.snacks.ApplePie;

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
