package com.desgin.create_type._02_factory_design.sample01.abstractfactory.products.hamburgers;

import com.desgin.create_type._02_factory_design.sample01.provider.IBurgers;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/6 14:57
 * description:
 */
public class BeefBurger implements IBurgers {
    @Override
    public String makeBurger() {
        return "牛肉汉堡";
    }
}
