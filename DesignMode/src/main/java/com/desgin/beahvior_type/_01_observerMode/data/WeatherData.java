package com.desgin.beahvior_type._01_observerMode.data;

import com.desgin.beahvior_type._01_observerMode.inter.ObserverZ;
import com.desgin.beahvior_type._01_observerMode.inter.Subject;

import java.util.ArrayList;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/5/28 17:39
 * description:天气数据处理中心
 */
public class WeatherData implements Subject{

    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList();
    }

    public WeatherData(ArrayList observers) {
        this.observers = observers;
    }

    @Override
    public void registerObserver(ObserverZ obj) {
        observers.add(obj);
    }

    @Override
    public void unregisterObserver(ObserverZ obj) {
        if(observers.indexOf(obj) >= 0){
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        System.out.println("WeatherData----循环遍历观察者，通知更新最新数据");
        for (Object observer1 : observers) {
            ObserverZ observer = (ObserverZ) observer1;
            observer.update(temperature, humidity, pressure);
        }
    }

    public void dataHasChanged(){
        System.out.println("WeatherData----数据变化，通知观察者");
        notifyObservers();
    }

    public void setData(float temp,float humidity,float pressure){
        temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        dataHasChanged();
    }
    //其他方法
}
