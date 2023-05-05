package com.kingz.godlike.date;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * author: King.Z
 * date:  2023/4/23 14:23
 * description: 时间工具类
 *
 * 【Date类】:
 *  是Java中最早的日期时间处理类之一，可以用于表示时间戳（即从1970年1月1日00:00:00开始的毫秒数）。
 *  Date类的主要作用是对时间戳进行操作，比如获取当前时间戳、将时间戳转换为Date对象等。
 *  [优点]：方便地进行时间戳的转换和比较;
 *  [缺点]：对于日期和时间信息的操作不如Calendar类方便;
 *
 *  常用API:
 *   {@link Date#compareTo(Date)} 用于比较两个日期的先后顺序，方式为前Date与后Date比较，如何前Date小，则返回负数。
 *
 * 【Calendar类】:
 *  Java中用于操作日期和时间信息的主要类之一。
 *  它提供了丰富的日期和时间操作方法，包括获取年、月、日、小时、分钟、秒等各种时间信息、设置指定时间、计算时间差等等。
 *  与Date类不同，Calendar类允许开发者在一个独立于特定时区的环境下进行日期和时间计算，并且支持国际化。
 *  同时，Calendar还提供了许多静态工厂方法，方便创建和获取Calendar实例。
 *
 * Java 8及以后版本增加了对新的日期时间API的支持:
 *  可以使用 instant 代替 Date;
 *  Localdatetime 代替 Calendar;
 *  Datetimeformatter 代替 Simpledateformatter，官方给出的解释：simple beautiful strong
 *  immutable thread-safe。
 *
 *  LocalDate、LocalTime、LocalDateTime等类
 */
public class TimeUtils {
    private static final long ONE_DAY_MS = TimeUnit.DAYS.toMillis(1);

    /**
     * 注意:  SimpleDateFormat 的format和parse方法，并非是线程安全
     */
    SimpleDateFormat ymdHmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    Date date;
    public String parseDate(Date date){
        return parseDate(date, ymdHmsFormat);
    }

    public String parseDate(Date date,SimpleDateFormat format){
        return parseDate(date, format, new ParsePosition(8));
    }

    public String parseDate(Date date,SimpleDateFormat format, ParsePosition parsePosition){
        if(format == null){
            format = ymdHmsFormat;
        }
        //Date parsedDate = _ymdHmsFormat.parse(currentTime, parsePosition);
        return format.format(new Date()); //自动地将时间转换为系统默认时区的时间;
    }

    // <editor-fold defaultstate="collapsed" desc="Calendar使用">
    Calendar calendar = Calendar.getInstance();
    String currentSysTime;

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
     * 计算固定格式的时间毫秒
     * @param inVal
     * @return 距1970年的时间毫秒数
     */
     public long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
         try {
             date = ymdHmsFormat.parse(inVal); //将字符型转换成日期型
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
             ymdHmsFormat = new SimpleDateFormat(format);
             date = ymdHmsFormat.parse(inVal);
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
        currentSysTime = ymdHmsFormat.format(new Date());
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = ymdHmsFormat.parse(currentSysTime, pos);
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
        ymdHmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = ymdHmsFormat.parse(strTime);
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
     * 获取当前时间的小时值
     * @return
     */
    private int getCurrentTimeHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 根据yyyy-MM-dd HH:mm:ss格式的日期返回星期几
     *
     * @return 星期X
     */
    public String getWeek(String ymdHms){
        try {
            Date date = ymdHmsFormat.parse(ymdHms);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "星期八";
        }
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
    // </editor-fold>

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


    public static void dumpCurrentWeekDate() {
        // 获取当前系统时间
        Calendar calendar = Calendar.getInstance();
        // 创建日期格式化字符串
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 计算本周第一天的日期（以周一为起始点）
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String firstDayOfWeek = dateFormat.format(calendar.getTime());
        // 输出本周日期
        System.out.println("This week:");
        for (int i = 0; i < 7; i++) {
            String dayOfWeek = dateFormat.format(calendar.getTime());
            System.out.println(dayOfWeek);
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
    }

    /**
     * 打印本周内周一到当天的日期
     */
    public static void dumpCurrentWeekDateUntilToday() {
        Calendar calendar = Calendar.getInstance();
        Date todayDate = calendar.getTime(); //当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 计算本周第一天的日期（以周一为起始点）
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // 输出本周日期
        System.out.println("This week:");
        for (int i = 0; i < 7; i++) {
            Date tmpDate = calendar.getTime();
            if(tmpDate.compareTo(todayDate) < 0){
                String dayOfWeek = dateFormat.format(tmpDate);
                System.out.println(dayOfWeek);
            }else{
                break;
            }
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
    }


}
