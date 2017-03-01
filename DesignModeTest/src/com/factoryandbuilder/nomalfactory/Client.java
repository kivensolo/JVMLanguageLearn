package com.factoryandbuilder.nomalfactory;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/1/25 14:29 <br>
 * description: 场景类 <br>
 */
public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
            /*
            * 继续业务处理
            */
    }
}
