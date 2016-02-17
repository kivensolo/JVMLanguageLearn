package com.factory.simplefactory.drinks;

import com.factory.simplefactory.woods.IDrinks;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/5 15:24
 * description:
 */
public class Cola implements IDrinks {
    @Override
    public String makeDrinks() {
        return "可乐";
    }
}
