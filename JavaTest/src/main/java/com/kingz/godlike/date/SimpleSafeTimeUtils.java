package com.kingz.godlike.date;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 演示简单的线程安全的DateFormat
 **/
public class SimpleSafeTimeUtils{
    /**
     * SimpleDateFormat 的parse、format等方法 并非是线程安全
     * 要想安全:
     * 1. 使用局部变量，保证每个线程中都有一份SimpleDateFormat实例(频繁创建对象)
     * 2. 使用 ThreadLocal，本质上 ThreadLocal 并不是用来保证 线程安全的。
     * 3. 同步代码块 synchronized
     * 4. Lock锁方式
     */
    //static SimpleDateFormat ymdformatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static Date parse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }

    /**
     * 基于JDK1.8的 DateTimeFormatter
     */
    public static String SyncFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String strDate = formatter.format(now); //DateTimeFormatter 自带的格式方法
        System.out.println(strDate);
        return strDate;
    }

}
