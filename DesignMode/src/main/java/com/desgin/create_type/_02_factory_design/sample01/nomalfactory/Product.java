package com.desgin.create_type._02_factory_design.sample01.nomalfactory;

/**
 * author: King.Z <br>
 * date:  2017/1/25 14:25 <br>
 * description: 抽象产品类 <br>
 */
public abstract class Product {
    //产品类的公共方法
    public void create_1() {
        //业务逻辑处理
    }

    //抽象方法
    public abstract void create();
}
