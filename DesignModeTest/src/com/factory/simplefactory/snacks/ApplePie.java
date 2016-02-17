package com.factory.simplefactory.snacks;

import com.factory.simplefactory.woods.ISnack;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/5 15:27
 * description:
 */
public class ApplePie implements ISnack {
    @Override
    public String makeSnack() {
        return "苹果派";
    }
}
