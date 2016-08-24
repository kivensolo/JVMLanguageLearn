package com.factoryandbuilder.provider;

import com.factoryandbuilder.abstractfactory.Order;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/8/24 11:05 <br>
 * description: 抽象工厂工厂所需要实现的公共接口 <br>
 *   将共同部分封装在抽象类中,不同部分使用子类实现
 */
public interface IAbstractProvider{
    /** 巨无霸套餐 */
    public Order createBigMacCombo();
    /** 鸡肉套餐 */
    public Order createCheckenCombo();
}
