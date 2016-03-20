package com.kingz.godlike;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/16 17:28
 * description:
 */
public class DateTest {
    public static void main(String[] args) {
        DateUtil  du = new DateUtil();
        String currentTime = du.getStringDate();
        System.out.println("当前系统时间：" + currentTime);
        du.caculateTimeDiff("2015-03-03 14:51:23","2016-03-03 15:51:53");
        du.getTimeZone();
        du.changeString2Date(du.getStringDate());
        du.getHour();
        du.getWeek(currentTime);
    }
}
