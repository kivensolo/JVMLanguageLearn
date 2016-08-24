package com.factoryandbuilder.abstractfactory.customer;

import com.factoryandbuilder.provider.IAbstractProvider;
import com.factoryandbuilder.abstractfactory.Combofactory.OrderFactory;
import com.factoryandbuilder.abstractfactory.Order;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/6 15:07
 * description: 顾客C点了 鸡肉套餐套餐  鸡肉汉堡+曲奇饼+可乐
 */
public class Customer_C {
    public static void main(String[] args) {
        IAbstractProvider provider = new OrderFactory();
        Order order = provider.createCheckenCombo();
        System.out.println(order.makeOrder());
    }
}
