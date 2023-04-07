package com.kingz.godlike.date;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/16 17:28
 * description:
 */
public class DateTest {

    public static void main(String[] args) {
        //DateUtil  du = new DateUtil();
        //String currentTime = du.getStringDate();
        //System.out.println("");
        //du.caculateTimeDiff("2015-03-03 14:51:23","2016-03-03 15:51:53");
        //
        //du.getTimeZone();
        //
        //System.out.println("");
        //du.changeString2Date(du.getStringDate());
        //du.getHour();
        //du.getWeek(currentTime);

        //String endDate = "2017-05-11 19:18:03";
        //endDate = endDate.replaceAll("-","").replaceAll(":","").replaceAll(" ","");
        //System.out.println("endDate = " + endDate);
        //DateUtil du = new DateUtil();
        //String current = du.getStringDate();
        //current = current.replaceAll("-","").replaceAll(":","").replaceAll(" ","");
        //System.out.println("current = " + current);
        //System.out.println(""+current.compareTo(endDate));

        DateUtil.dumpCurrentWeekDateUntilToday();
    }
}
