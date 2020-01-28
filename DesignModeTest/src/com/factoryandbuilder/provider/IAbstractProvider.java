package com.factoryandbuilder.provider;

import com.factoryandbuilder.abstractfactory.Order;

/**
 * author: King.Z <br>
 * date:  2016/8/24 11:05 <br>
 * description: 抽象工厂模式的抽象工厂类 <br>
 *   将共同部分封装在抽象类中,不同部分使用子类实现
 */
public interface IAbstractProvider{
    /** 巨无霸套餐 */
    Order createBigMacCombo();
    /** 鸡肉套餐 */
    Order createCheckenCombo();
}
