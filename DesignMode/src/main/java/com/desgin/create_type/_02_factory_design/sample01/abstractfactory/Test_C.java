package com.desgin.create_type._02_factory_design.sample01.abstractfactory;

import com.desgin.create_type._02_factory_design.sample01.provider.IAbstractProvider;

/**
 * author: King.Z
 * date:  2016/1/6 15:07
 * description: 顾客C点了 鸡肉套餐套餐  鸡肉汉堡+曲奇饼+可乐
 */
public class Test_C {
    public static void main(String[] args) {
        IAbstractProvider provider = new OrderFactory();
        Order order = provider.createCheckenCombo();
        System.out.println(order.makeOrder());
    }
}
