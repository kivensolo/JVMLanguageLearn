package com.factoryandbuilder.abstractfactory.customer;

import com.factoryandbuilder.abstractfactory.Order;
import com.factoryandbuilder.abstractfactory.drinks.Cola;
import com.factoryandbuilder.abstractfactory.snacks.ChocolateShack;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/6 14:46
 * description:顾客B点了：可乐 + 巧克力奶昔
 */
public class Customer_B {
     public static void main(String[] args) {
        Order order = new Order.OrderBuilder()
                .addDrinks(new Cola())
                .addSnack(new ChocolateShack())
                .build();
        System.out.println("Customer_B ：" + order.makeOrder());
    }
}
