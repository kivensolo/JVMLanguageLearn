package com.desgin.create_type._02_factory_design.sample01.abstractfactory.products.hamburgers;

import com.desgin.create_type._02_factory_design.sample01.provider.IBurgers;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/5 14:10
 * description:
 */
public class CheckenBurgur implements IBurgers {

    @Override
    public String makeBurger() {
        return "鸡肉汉堡";
    }
}
