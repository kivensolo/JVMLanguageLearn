package com.kingz.base.date;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 常用日期工具类:
 *
 * 获取指定日期前后几天的日期      {@link #getDateOffset} <br>
 * 获取指定日期前后多少天是星期几   {@link #getDateOffsetDayAtWeek} <br>
 * 判断一个日期是否是周末          {@link #isWeekend} <br>
 * 获取某个日期在一周中的顺序       {@link #getWeekIndexFromClendar} <br>
 */
public class CalendarUtil {

    private static final long ONE_DAY = 1000 * 3600 * 24;

    private static final List BigMonthList = new ArrayList<>(Arrays.asList(
            1, 3, 5, 7, 8, 10, 12));
    private static final List SmallMonthList = new ArrayList<>(Arrays.asList(
            2, 4, 6, 9, 11));

    //周起始：周日  默认模式
    private static final int WEEK_START_WITH_SUN = 1;
    //周起始：周一
    private static final int WEEK_START_WITH_MON = 2;
    //周起始：周六
    private static final int WEEK_START_WITH_SAT = 7;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    private static ArrayList weekDays = new ArrayList<>(Arrays.asList(
            "星期天", "星期一", "星期二", "星期三",
            "星期四", "星期五", "星期六"));

    /**
     * 获取指定日期前后几天的日期
     *
     * @param date    指定日期
     * @param offsets 日期偏移量(天) 正负
     * @return Date 目标日期
     */
    public static Date getDateOffset(Date date, int offsets) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offsets);
        return calendar.getTime();
    }

    /**
     * 获取指定日期前后多少天是星期几
     *
     * @param date    指定日期
     * @param offsets 日期偏移量(天) 正负
     * @return 星期几
     * <p>
     */
    public static String getDateOffsetDayAtWeek(Date date, int offsets) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offsets);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        String offsetDayOfWeek;
        if (calendar.get(Calendar.DAY_OF_WEEK) <= weekDays.size()) {
            offsetDayOfWeek = (String) weekDays.get(index - 1);
        } else {
            offsetDayOfWeek = (String) weekDays.get(0);
        }
        return offsetDayOfWeek;
    }

    /**
     * 判断一个日期是否是周末，即周六日
     *
     * @param date 具体日期
     * @return 判断一个日期是否是周末，即周六日
     */
    public static boolean isWeekend(Date date) {
        int week = getWeekIndexFromClendar(date) - 1;
        return week == 0 || week == 6;
    }


    /**
     * FIXME 计算不对
     * 获取某天在该月的第几周,换言之就是获取这一天在该月视图的第几行,第几周.
     * 根据周起始动态获取
     * @param date      date
     * @param weekStart weekStart
     * @return 获取某天在该月的第几周 The week line in MonthView
     */
   public static int getWeekFromDayInMonth(Date date, int weekStart) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //该月第一天为星期几,星期天 == 0
        int diff = getMonthStartDiff(calendar, weekStart);
        //FIXME 算法探究 加上偏移量 - 1 除以7？ 计算不对
        return (calendar.get(Calendar.DAY_OF_MONTH) + diff - 1) / 7 + 1;
    }

    /**
     * 获取日期所在月对应的起始偏移量，以周日为起始计算。
     *
     * @param calendar  calendar
     * @return 获取日期所在月中对应的起始偏移量
     */
    public static int getMonthStartDiff(Calendar calendar) {
        return getMonthStartDiff(calendar, WEEK_START_WITH_SUN);
    }

    /**
     * DAY_OF_WEEK return  1  2  3 	4  5  6	 7，偏移了一位
     * 获取日期所在月对应的起始偏移量
     *
     * @param calendar  calendar
     * @param weekStart 起始星期模式，1.周日、 2.周一、 7.周六
     * @return 获取日期所在月中对应的起始偏移量 The start diff with Month(From 0)
     */
    public static int getMonthStartDiff(Calendar calendar, int weekStart) {
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekStart == WEEK_START_WITH_SUN) {
            // 以周日开始，偏移量为0( 1-1 )
            return week - 1;
        }else if(weekStart == WEEK_START_WITH_MON) {
            // 以周一开始  若为周日 偏移量为6
            return week == 1 ? 6 : week - WEEK_START_WITH_MON;
        }else{
            // 以周六开始  若为周六 偏移量为0
            return week == WEEK_START_WITH_SAT ? 0 : week;
        }
    }



    /**
     * 获取某年某月，当月的天数
     *
     * @param year  年
     * @param month 月
     * @return 某月的天数
     */
    public static int getMonthDaysCount(int year, int month) {
        int count = 0;
        //判断大月份
        if(BigMonthList.contains(month)){
           count = 31;
        }
        //判断小月
        if(SmallMonthList.contains(month)){
           count = 30;
        }
        //判断平年与闰年
        if (month == 2) {
            if (isLeapYear(year)) {
                count = 29;
            } else {
                count = 28;
            }
        }
        return count;
    }


    /**
     * 是否是闰年
     *
     * @param year 指定年份
     * @return 是否是闰年
     */
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    /**
     * 获取某个日期在一周中的顺序
     * 测试通过
     *
     * @param date 具体日期
     * @return 返回某个日期是星期几
     *
     * calendar.get(Calendar.DAY_OF_WEEK) 1为星期天，7为星期6
     */
    public static int getWeekIndexFromClendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 是否在日期范围內
     * 测试通过 test pass
     *
     * @param date         date
     * @param minYear      minYear
     * @param minYearDay   最小年份天
     * @param minYearMonth minYearMonth
     * @param maxYear      maxYear
     * @param maxYearMonth maxYearMonth
     * @param maxYearDay   最大年份天
     * @return 是否在日期范围內
     */
    static boolean isCalendarInRange(Date date,
                                     int minYear, int minYearMonth, int minYearDay,
                                     int maxYear, int maxYearMonth, int maxYearDay){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long curTimeMs = calendar.getTimeInMillis();
        calendar.set(minYear,minYearMonth,minYearDay);
        long minTimeMs = calendar.getTimeInMillis();

        calendar.set(maxYear,maxYearMonth,maxYearDay);
        long maxTimeMs = calendar.getTimeInMillis();
        return curTimeMs >= minTimeMs && curTimeMs <= maxTimeMs;
    }

    /**
     * TODo 没测试过
     * 获取两个日期之间一共有多少周，
     * 注意周起始周一、周日、周六
     *
     * @param minYear      minYear 最小年份
     * @param minYearMonth maxYear 最小年份月份
     * @param minYearDay   最小年份天
     * @param maxYear      maxYear 最大年份
     * @param maxYearMonth maxYear 最大年份月份
     * @param maxYearDay   最大年份天
     * @param weekStart    周起始
     * @return 周数用于WeekViewPager itemCount
     */
    public static int getWeekCountBetweenBothCalendar(int minYear, int minYearMonth, int minYearDay,
                                                      int maxYear, int maxYearMonth, int maxYearDay,
                                                      int weekStart) {
        java.util.Calendar date = Calendar.getInstance();
        date.set(minYear, minYearMonth - 1, minYearDay);
        long minTimeMills = date.getTimeInMillis();
        int preDiff = getWeekViewStartDiff(minYear, minYearMonth, minYearDay, weekStart);

        date.set(maxYear, maxYearMonth - 1, maxYearDay);

        long maxTimeMills = date.getTimeInMillis();//给定时间戳

        int nextDiff = getWeekViewEndDiff(maxYear, maxYearMonth, maxYearDay, weekStart);

        int count = preDiff + nextDiff;

        int c = (int) ((maxTimeMills - minTimeMills) / ONE_DAY) + 1;
        count += c;
        return count / 7;
    }

    /**
     *  TODo 我没测试过
     * 单元测试通过
     * 从选定的日期，获取周视图起始偏移量，用来生成周视图布局
     *
     * @param year      year
     * @param month     month
     * @param day       day
     * @param weekStart 周起始，1，2，7 日 一 六
     * @return 获取周视图起始偏移量，用来生成周视图布局
     */
    private static int getWeekViewStartDiff(int year, int month, int day, int weekStart) {
        Calendar date = Calendar.getInstance();
        date.set(year, month - 1, day, 12, 0);
        int week = date.get(Calendar.DAY_OF_WEEK);
        if (weekStart == 1) {
            return week - 1;
        }
        if (weekStart == 2) {
            return week == 1 ? 6 : week - 2;
        }
        return week == 7 ? 0 : week;
    }
    /**
     * TODo 我没测试过
     * 单元测试通过
     *
     * 从选定的日期，获取周视图结束偏移量，用来生成周视图布局
     * 为了兼容DST，DST时区可能出现时间偏移1-2小时，从而导致凌晨时候实际获得的日期往前或者往后推移了一天，
     * 日历没有时和分的概念，因此把日期的时间强制在12:00，可以避免DST兼容问题
     *
     * @param year      year
     * @param month     month
     * @param day       day
     * @param weekStart 周起始，1，2，7 日 一 六
     * @return 获取周视图结束偏移量，用来生成周视图布局
     */
    public static int getWeekViewEndDiff(int year, int month, int day, int weekStart) {
        Calendar date = Calendar.getInstance();
        date.set(year, month - 1, day, 12, 0);
        int week = date.get(Calendar.DAY_OF_WEEK);
        if (weekStart == 1) {
            return 7 - week;
        }
        if (weekStart == 2) {
            return week == 1 ? 0 : 7 - week + 1;
        }
        return week == 7 ? 6 : 7 - week - 1;
    }

}
