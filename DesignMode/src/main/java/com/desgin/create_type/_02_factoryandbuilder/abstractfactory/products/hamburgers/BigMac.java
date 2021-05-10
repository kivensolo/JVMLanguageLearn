package com.desgin.create_type._02_factoryandbuilder.abstractfactory.products.hamburgers;

import com.desgin.create_type._02_factoryandbuilder.provider.IBurgers;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/5 14:06
 * description:
 */
public class BigMac implements IBurgers {
    @Override
    public String makeBurger() {
        return "巨无霸";
    }
}
