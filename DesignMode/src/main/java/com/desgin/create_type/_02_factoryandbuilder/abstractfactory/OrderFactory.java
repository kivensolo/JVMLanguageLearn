package com.desgin.create_type._02_factoryandbuilder.abstractfactory;

import com.desgin.create_type._02_factoryandbuilder.abstractfactory.products.drinks.Cola;
import com.desgin.create_type._02_factoryandbuilder.abstractfactory.products.drinks.Milk;
import com.desgin.create_type._02_factoryandbuilder.abstractfactory.products.hamburgers.BigMac;
import com.desgin.create_type._02_factoryandbuilder.abstractfactory.products.hamburgers.CheckenBurgur;
import com.desgin.create_type._02_factoryandbuilder.abstractfactory.products.snacks.ApplePie;
import com.desgin.create_type._02_factoryandbuilder.abstractfactory.products.snacks.Cookies;
import com.desgin.create_type._02_factoryandbuilder.provider.IAbstractProvider;

/**
 * author: King.Z
 * date:  2016/1/6 15:02
 * description: 抽象工厂模式中的具体工厂实现类
 */
public class OrderFactory implements IAbstractProvider {
    @Override
    public Order createBigMacCombo() {
        System.out.println("工厂--->创建巨无霸套餐");
        return new Order.OrderBuilder()
                        .addBurger(new BigMac())
                        .addSnack(new ApplePie())
                        .addDrinks(new Milk())
                        .build();
    }

    @Override
    public Order createCheckenCombo() {
        System.out.println("工厂--->创建鸡肉套餐");
        return new Order.OrderBuilder()
                        .addBurger(new CheckenBurgur())
                        .addSnack(new Cookies())
                        .addDrinks(new Cola())
                        .build();
    }
}
