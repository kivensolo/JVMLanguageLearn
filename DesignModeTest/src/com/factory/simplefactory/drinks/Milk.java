package com.factory.simplefactory.drinks;

import com.factory.simplefactory.woods.IDrinks;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/5 15:26
 * description:
 */
public class Milk implements IDrinks{
    @Override
    public String makeDrinks() {
        return "饮料---->牛奶";
    }
}
