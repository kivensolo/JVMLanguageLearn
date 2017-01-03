package com.hello;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/6/29 18:09 <br>
 * description: XXXXXXX <br>
 */
public class TEst {
    public static void main(String[] args) {
        //System.out.println(String.valueOf(Integer.valueOf("0106")));
        //String desc = "";
        //Pattern p = Pattern.compile("\\n");
        //Matcher m = p.matcher(desc);
        //desc = m.replaceAll("");
        ////desc+="\n";
        //System.out.println(desc);

        String url ="rtsp://192.168.90.43:5202/ZGDX/01dianxin4?Authinfo=GEYYDTH19BWNj3KHosDStxs9wO78BUk4MYcbyOgzMP159aZOFKLh6dkEmzKDADq%2B&amp;playseek=20161118T065910_000Z";
        //String url ="asasasplayseek=20161118T065910_000Z";
        int index = url.indexOf("playseek=");
        System.out.println(index);
        String start = url.substring(0,index);
        String oldEnd = url.substring(index).substring("playseek=".length());
        System.out.println("前部分："+start);
        System.out.println("后部分:"+oldEnd);
        String endString = oldEnd.substring(15);
        System.out.println(endString);
        String newEnd = "20145896"+"T"+"050807"+endString;
        System.out.println("新串："+start+"playseek="+newEnd);

    }
}
