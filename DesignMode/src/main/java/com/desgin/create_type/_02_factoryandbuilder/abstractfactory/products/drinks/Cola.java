package com.desgin.create_type._02_factoryandbuilder.abstractfactory.products.drinks;

import com.desgin.create_type._02_factoryandbuilder.provider.IDrinks;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/5 15:24
 * description: 饮料--可乐
 */
public class Cola implements IDrinks {
    @Override
    public String makeDrinks() {
        return "可乐";
    }
}
