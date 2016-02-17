package com.factory.simplefactory.customer;

import com.factory.simplefactory.Order;
import com.factory.simplefactory.drinks.Cola;
import com.factory.simplefactory.snacks.ChocolateShack;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/6 14:46
 * description:
 */
public class Customer_B {
     public static void main(String[] args) {
        Order order = new Order.OrderBuilder()
                .addDrinks(new Cola())
                .addSnack(new ChocolateShack()).build();
        System.out.println("Customer_B ：" + order.makeOrder());
    }
}
