package com.kingz.base.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");


    public static void main(String[] args) {
        Date date = new Date();
        //计算3天前的日期
        Date offsetDate = CalendarUtil.getDateOffset(date, -3);
        Date offsetDate2 = CalendarUtil.getDateOffset(date, 3);
        System.out.println(String.format("3天前的日期: %s , 星期: %s",
                sdf.format(offsetDate),
                CalendarUtil.getDateOffsetDayAtWeek(date, -3)
        ));
        System.out.println(String.format("3天后的日期: %s , 星期: %s",
                sdf.format(offsetDate2),
                CalendarUtil.getDateOffsetDayAtWeek(date, 3)
        ));


        TestWeekInfoFromStartDate();
    }

    private static void TestWeekInfoFromStartDate() {
        Calendar calendar = Calendar.getInstance();
        // 注意：月份基数是0
        calendar.set(2021, 7, 30);
        int weekFromDayInMonth = CalendarUtil.getWeekFromDayInMonth(calendar.getTime(), 2);
        System.out.println(String.format("日期在当月处于第几周: %d",weekFromDayInMonth
        ));

        System.out.println();
        // 计算当前时间往后15天内，每一天属于那一周，并且日期是多少
        int weekStartIndex = calendar.get(Calendar.DAY_OF_WEEK) - 2; //以周一为起始的偏移量
        for (int i = 1; i <= 15; i++) {
            // 增加偏移量
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            // 计算日期距离起始时间的周数  需区分起始类型
            int weekCount = (weekStartIndex + i) / 7 + 1; // 周数

            // 计算日期的时间
            Date dateNew = calendar.getTime();
            String newData = sdf.format(dateNew);
            System.out.println(String.format("日期：%s,距离起始时间周数为：%d", newData, weekCount));
        }
    }


}
