package com.desgin.beahvior_type._01_observerMode.inter;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/5/28 17:34
 * description:观察者接口
 */
public interface ObserverZ {
    //温度、湿度、压力更新通知接口
    public void update(float temp,float humidity,float pressure);
}
