package com.factoryandbuilder.abstractfactory.drinks;

import com.factoryandbuilder.provider.IDrinks;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/5 15:26
 * description:饮料----橙汁
 */
public class OrangeJuice implements IDrinks {
    @Override
    public String makeDrinks() {
        return "橙汁";
    }
}
