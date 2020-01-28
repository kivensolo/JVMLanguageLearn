package com.factoryandbuilder.abstractfactory.products.snacks;

import com.factoryandbuilder.provider.ISnack;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/6 14:57
 * description:
 */
public class Cookies implements ISnack {
    @Override
    public String makeSnack() {
        return "曲奇饼";
    }
}
