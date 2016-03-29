package com.kingz.godlike;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/16 17:23
 * description: 时间工具类
 */
public class DateUtil {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final int ONE_DAY_MS = 86400000;
    Calendar calendar = Calendar.getInstance();
    String currentSysTime;
    DateFormat df;
    Date date;

    /**
	 * 每日定时定点做事
	 */
	private void setExpireCheckClock() {
		Calendar cal = Calendar.getInstance();
		//每天(24小时制)定点执行
		cal.set(Calendar.HOUR_OF_DAY, 18);
		cal.set(Calendar.MINUTE, 15);
		cal.set(Calendar.SECOND, 0);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
                //
			}
		}, cal.getTime(), ONE_DAY_MS);
	}


    /**
     * 获取现在的时间
     * @return 字符串格式  yyyy-MM-dd HH:mm:ss
     */
    public String getStringDate(){
        currentSysTime = sdf.format(new Date());  //new Date为获取当前系统时间
        System.out.println("当前系统时间："+currentSysTime);
        return currentSysTime;
    }

    /**
     * 获取当前时间
     * @return 字符串格式  yyyy年MM月dd日
     */
    public String getCurrentTextDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        currentSysTime = sdf.format(new Date());
        System.out.println("当前系统时间："+currentSysTime);
        return currentSysTime;
    }

    /**
     * 获取现在时间
     * @return 返回短时间字符串格式 yyyy-MM-dd
     */
    public static String getStringDateShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    /**
     * 计算固定格式的时间毫秒
     * @param inVal
     * @return 距1970年的时间毫秒数
     */
     public long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
         try {
             date = sdf.parse(inVal); //将字符型转换成日期型
         } catch (Exception e) {
             e.printStackTrace();
         }
         return date.getTime();
     }

    /**
     * 计算自定义格式的时间毫秒
     * @param inVal
     * @param format 自定义时间格式
     * @return 距1970年的时间毫秒数
     */
     public long fromDateStringToLong(String inVal,String format) {
         try {
             sdf = new SimpleDateFormat(format);
             date = sdf.parse(inVal);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return date.getTime();
     }

    /**
     * 获取当前日期
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public Date getNowDate(){
        currentSysTime = sdf.format(new Date());
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = sdf.parse(currentSysTime, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     * @return 返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

     /**
     * 计算两个时间点之差  类型：XX天XX小时XX分XX秒
     * @param startTime
     * @param endTime
     */
    public void caculateTimeDiff(String startTime,String  endTime){
        long startT = fromDateStringToLong(startTime);
        long endT = fromDateStringToLong(endTime);
        long diffms = endT - startT;        //共计毫秒数
        long days = diffms / (1000 * 60 * 60 * 24);
        long hours = (diffms - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diffms-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
        long secondss = (diffms-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60)- minutes*(1000* 60))/1000;

        long ss = diffms/1000;              //共计秒数
        int MM = (int)ss/60;                //共计分钟数
        int hh=(int)ss/3600;                //共计小时数
        int dd= hh /24;                  //共计天数
        System.out.println("------------------两个时间点之间------------------");
        System.out.println("共"+days+"天"+hours+"小时"+minutes+"分"+secondss+"秒");
        System.out.println("天数："+dd+"天；  总小时数："+hh+"h; 总分钟数："+MM+"min； 总秒数："+ss+"s； 共计："+ss*1000+" ms");
        System.out.println("-------------------------------------------------");
    }

    /**
     * 获取时区
     */
    public String getTimeZone(){
        String timeZone = calendar.getTimeZone().getDisplayName(Locale.CHINA);
        System.out.println("时区名称："+timeZone);
        return timeZone;
    }

    /**
     * String 类型的时间转为Date类型
     */
    public void changeString2Date(String strTime){
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = sdf.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("StringToDate："+time);
    }

    /**
     * 获取现在的小时
     */
    public int getHour(){
        int hour_24 =  calendar.get(Calendar.HOUR_OF_DAY);
        int hour_12 =  calendar.get(Calendar.HOUR);
        System.out.println("当前小时数（24）:"+ hour_24+"    12时制:" + hour_12 );
        return hour_24;
    }

    /**
     * 根据日期返回星期几
     * @return 星期X
     */
    public String getWeek(String strDate){
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String weekStr = "";
        switch (day){
            case 1:
                weekStr = "星期日";
                break;
            case 2:
                weekStr = "星期一";
                break;
            case 3:
                weekStr = "星期二";
                break;
            case 4:
                weekStr = "星期三";
                break;
            case 5:
                weekStr = "星期四";
                break;
            case 6:
                weekStr = "星期五";
                break;
            case 7:
                weekStr = "星期六";
                break;
        }
        System.out.println("星期X：" + weekStr);
        return weekStr;
    }
}
