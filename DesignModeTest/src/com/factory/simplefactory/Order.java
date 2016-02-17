package com.factory.simplefactory;

import com.factory.simplefactory.woods.IBurgers;
import com.factory.simplefactory.woods.IDrinks;
import com.factory.simplefactory.woods.ISnack;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/5 15:29
 * description: 订单类 可以随意组合小吃、饮料、汉堡
 */
public class Order {
    private IBurgers mBurger;
    private ISnack mSnack;
    private IDrinks mDrinks;

    public Order(OrderBuilder builder) {
        mBurger = builder.mBurger;
        mSnack = builder.mSnack;
        mDrinks = builder.mDrinks;
    }

    /**
     * 生成订单
     * @return
     */
    public String makeOrder(){
        StringBuffer sbd = new StringBuffer();
        if(null != mBurger){
            sbd.append(mBurger.makeBurger()).append(" ");
        }
        if( null != mSnack){
            sbd.append(mSnack.makeSnack()).append(" ");
        }
        if(null != mDrinks){
            sbd.append(mDrinks.makeDrinks()).append(" ");
        }
        return sbd.toString();
    }

    /**
     * 嵌套类
     * static的内部类就叫做嵌套类，
     * a、创建嵌套类对象时，不需要外围类
     * b、在嵌套类中，不能像普通内部类一样访问外围类的非static成员
     * c、嵌套类中可以有static方法，static字段与嵌套类，而普通内部类中不能有这些
     */
    public static class OrderBuilder{
        private IBurgers mBurger;
        private ISnack mSnack;
        private IDrinks mDrinks;

        public OrderBuilder() {
        }

        public OrderBuilder addBurger(IBurgers mBurger){
            this.mBurger = mBurger;
            return this;
        }
        public  OrderBuilder addSnack(ISnack mSnack){
            this.mSnack = mSnack;
            return this;
        }
        public  OrderBuilder addDrinks(IDrinks mDrinks){
            this.mDrinks = mDrinks;
            return this;
        }
        public Order build(){
            return new Order(this);
        }

    }
}
