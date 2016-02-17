package com.factory.simplefactory.Combofactory;

import com.factory.simplefactory.Order;
import com.factory.simplefactory.drinks.Cola;
import com.factory.simplefactory.drinks.Milk;
import com.factory.simplefactory.hamburgers.BigMac;
import com.factory.simplefactory.hamburgers.CheckenBurgur;
import com.factory.simplefactory.snacks.ApplePie;
import com.factory.simplefactory.snacks.Cookies;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/6 15:02
 * description: 套餐--->工厂模式
 */
public class OrderFactory {
    /**
     * 巨无霸套餐
     * @return
     */
    public static Order createBigMacCombo(){
        System.out.println("工厂--->创建巨无霸套餐");
        Order order = new Order.OrderBuilder()
                .addBurger(new BigMac())
                .addSnack(new ApplePie())
                .addDrinks(new Milk())
                .build();
        return order;
    }

    /**
     * 鸡肉套餐
     */
      public static Order createCheckenCombo(){
        System.out.println("工厂--->创建鸡肉套餐");
        Order order = new Order.OrderBuilder()
                .addBurger(new CheckenBurgur())
                .addSnack(new Cookies())
                .addDrinks(new Cola())
                .build();
        return order;
    }
}
