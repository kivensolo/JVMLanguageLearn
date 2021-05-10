package com.desgin.beahvior_type._01_observerMode.data;

import com.desgin.beahvior_type._01_observerMode.inter.DisplayElement;
import com.desgin.beahvior_type._01_observerMode.inter.ObserverZ;
import com.desgin.beahvior_type._01_observerMode.inter.Subject;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/5/28 17:46
 * description:模拟显示屏显示数据
 */
public class CurrentConditionDisplay implements ObserverZ,DisplayElement{

    private Subject weatherData;
    private float temperature;
    private float humidity;

    public CurrentConditionDisplay(Subject weatherData) {
        System.out.println("CurrentConditionDisplay-----注册观察者");
        this.weatherData = weatherData;
        weatherData.registerObserver(this); //注册
    }

    @Override
    public void display() {
        System.out.println("当前温度："+temperature+";湿度："+humidity);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("CurrentConditionDisplay-----显示屏收到数据，显示：");
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
}
