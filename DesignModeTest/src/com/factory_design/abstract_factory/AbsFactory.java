package com.factory_design.abstract_factory;

/**
 * 抽象工厂
 */
abstract class AbsFactory {
    //生产中国披萨
    abstract CommonPizza createChinesePizza();
    //生产芝加哥披萨
    abstract CommonPizza createHotPizza();
}
