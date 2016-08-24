package com.factoryandbuilder.abstractfactory.Combofactory;

import com.factoryandbuilder.provider.IAbstractProvider;
import com.factoryandbuilder.abstractfactory.Order;
import com.factoryandbuilder.abstractfactory.drinks.Cola;
import com.factoryandbuilder.abstractfactory.drinks.Milk;
import com.factoryandbuilder.abstractfactory.hamburgers.BigMac;
import com.factoryandbuilder.abstractfactory.hamburgers.CheckenBurgur;
import com.factoryandbuilder.abstractfactory.snacks.ApplePie;
import com.factoryandbuilder.abstractfactory.snacks.Cookies;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/6 15:02
 * description: 套餐--->工厂模式
 */
public class OrderFactory implements IAbstractProvider {
    @Override
    public Order createBigMacCombo() {
        System.out.println("工厂--->创建巨无霸套餐");
        Order order = new Order.OrderBuilder()
                                .addBurger(new BigMac())
                                .addSnack(new ApplePie())
                                .addDrinks(new Milk())
                                .build();
        return order;
    }

    @Override
    public Order createCheckenCombo() {
        System.out.println("工厂--->创建鸡肉套餐");
        Order order = new Order.OrderBuilder()
                                .addBurger(new CheckenBurgur())
                                .addSnack(new Cookies())
                                .addDrinks(new Cola())
                                .build();
        return order;
    }
}
