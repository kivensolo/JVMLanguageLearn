package com.think.in;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/15 14:03
 * description: 日期格式化
 */
public class StringFormat {

    public static void main(String[] args) {
        //String data = "2016_01_1_";
        //data = data.replaceAll("_","");
        //System.out.println(data);
        String  end_str= "2019-10-18 23:59:59";
        long serviceTime = 1543129200;

        SimpleDateFormat endDataFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        //SimpleDateFormat endDataFormat2 = new SimpleDateFormat("HH:mm:ss");
        try {
            //Date date = endDataFormat.parse(end_str);
            //String strs = endDataFormat2.format(date);
            //System.out.println("转换后的日期：" + strs);

            Date date2 = new Date(Long.valueOf(String.valueOf(serviceTime)) * 1000);
            //Date date3 = endDataFormat.parse(serviceTime);
            //System.out.println(date2.toString());
            String nowTime = endDataFormat.format(date2);

            System.out.println("转换后的当前系统时间 CurrentServerTime = " + nowTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
         //String endData = end_str.replace("-","").replace(" ","").replace(":","");
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //String nowTime = sdf.format(new Date(currentServerTime));
    }
}
