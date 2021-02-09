package com.hello;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/1/11 11:15 <br>
 * description: XXXXXXX <br>
 */
public class Test_1 {
    public static void main(String[] args) {
        //int head = 4;
        //for (int i = 0; i < 4; i++) {
        //    head = (head + 1) & 5;
        //    System.out.println("head = " + head);
        //}
        String line = "/th?id=OHR.SkelligMichael_ZH-CN8635121409_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp";
        Pattern pattern = Pattern.compile("^.*(\\.jpg|\\.png).+$");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println();
        }
    }
}
