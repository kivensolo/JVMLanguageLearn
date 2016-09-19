package com.observerMode;

import com.observerMode.data.CurrentConditionDisplay;
import com.observerMode.data.WeatherData;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/5/28 17:52
 * description:气象站oo设计
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData data = new WeatherData();
        CurrentConditionDisplay display = new CurrentConditionDisplay(data);
        data.setData(22,78,30.4f);
        data.setData(16,72,22f);
        data.setData(13,65,44f);
    }
}
