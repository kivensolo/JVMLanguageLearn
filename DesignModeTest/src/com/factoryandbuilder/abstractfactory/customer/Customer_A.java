package com.factoryandbuilder.abstractfactory.customer;

import com.factoryandbuilder.abstractfactory.Order;
import com.factoryandbuilder.abstractfactory.drinks.OrangeJuice;
import com.factoryandbuilder.abstractfactory.hamburgers.CheckenBurgur;
import com.factoryandbuilder.abstractfactory.snacks.ApplePie;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/6 14:32
 * description: 顾客A点了：鸡肉汉堡 + 橙汁 + 苹果派
 */
public class Customer_A {
    public static void main(String[] args) {
        Order order = new Order.OrderBuilder()
                .addBurger(new CheckenBurgur())
                .addDrinks(new OrangeJuice())
                .addSnack(new ApplePie())
                .build();
        System.out.println("Customer_A ：" + order.makeOrder());
    }
}
