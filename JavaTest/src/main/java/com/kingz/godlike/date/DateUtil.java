package com.kingz.godlike.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/16 17:23
 * description: 时间工具类
 */
public class DateUtil {

    SimpleDateFormat _ymdHmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat _hmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final long ONE_DAY_MS = TimeUnit.DAYS.toMillis(1);
    Calendar calendar = Calendar.getInstance();
    String currentSysTime;
    DateFormat df;
    Date date;

    /**
	 * 每日定时定点执行
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
     * 获取当前时间的小时值
     * @return
     */
    private int getCurrentTimeHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 获取当前(系统)的时间
     * @param format 时间格式化样式
     * @return 字符串格式
     * 具体格式根据
     * 如： yyyy-MM-dd HH:mm:ss
     */
    public String getStringDate(SimpleDateFormat format){
        currentSysTime = format.format(new Date());  //new Date为获取当前系统时间
        System.out.println("当前系统时间："+currentSysTime);
        return format.format(new Date());
    }

    /**
     * 计算固定格式的时间毫秒
     * @param inVal
     * @return 距1970年的时间毫秒数
     */
     public long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
         try {
             date = _ymdHmsFormat.parse(inVal); //将字符型转换成日期型
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
             _ymdHmsFormat = new SimpleDateFormat(format);
             date = _ymdHmsFormat.parse(inVal);
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
        currentSysTime = _ymdHmsFormat.format(new Date());
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = _ymdHmsFormat.parse(currentSysTime, pos);
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
        _ymdHmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = _ymdHmsFormat.parse(strTime);
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
            date = _ymdHmsFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String weekStr = "";
        switch (day){
            case Calendar.SUNDAY:
                weekStr = "星期日";
                break;
            case Calendar.MONDAY:
                weekStr = "星期一";
                break;
            case Calendar.TUESDAY:
                weekStr = "星期二";
                break;
            case Calendar.WEDNESDAY:
                weekStr = "星期三";
                break;
            case Calendar.THURSDAY:
                weekStr = "星期四";
                break;
            case Calendar.FRIDAY:
                weekStr = "星期五";
                break;
            case Calendar.SATURDAY:
                weekStr = "星期六";
                break;
        }
        System.out.println("星期X：" + weekStr);
        return weekStr;
    }

     /**
     * 秒转换为指定格式的日期
     * @param second 秒数
     * @param patten 时间格式
     * @return 指定的时间格式
      * @Fixme 地区问题
     */
    public static String secondToDate(long second,String patten) {
        Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(second) - TimeUnit.HOURS.toMillis(8));
        calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(second) - TimeUnit.HOURS.toMillis(8));
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        return format.format(date);
    }

    /**
     * 秒转换为hh:mm:ss的日期
     * @param second 秒数
     * @return "02:59:59"
     */
    public static String secondToDate(int second) {
       int h = (int) (second / TimeUnit.HOURS.toSeconds(1));
       second = (int) (second - TimeUnit.HOURS.toSeconds(h));
       int m = (int) (second / TimeUnit.MINUTES.toSeconds(1));
       second = (int) (second - TimeUnit.MINUTES.toSeconds(m));
       return String.format("%02d:%02d:%02d",h,m,second);
    }
}
