package com.factory.simplefactory.customer;

import com.factory.simplefactory.Combofactory.OrderFactory;
import com.factory.simplefactory.Order;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/6 15:07
 * description:
 */
public class Customer_C {
    public static void main(String[] args) {
        Order order = OrderFactory.createCheckenCombo();
        System.out.println(order.makeOrder());
    }
}
