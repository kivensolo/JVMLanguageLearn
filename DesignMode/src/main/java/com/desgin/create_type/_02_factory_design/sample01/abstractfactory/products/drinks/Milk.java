package com.desgin.create_type._02_factory_design.sample01.abstractfactory.products.drinks;

import com.desgin.create_type._02_factory_design.sample01.provider.IDrinks;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/5 15:26
 * description:饮料---牛奶
 */
public class Milk implements IDrinks{
    @Override
    public String makeDrinks() {
        return "饮料---->牛奶";
    }
}
