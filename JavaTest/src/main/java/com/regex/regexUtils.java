package com.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexUtils {

    /**
     * 通过图片url获取图片的id名称
     * @param url
     *  eg:
     *  "http://img.epg.vod.chinaiptv.cn:50081/nn_img/prev/StarCor/epgimg/f/f/c/c942bc0eb7b1b781fd9fbac19613cd1f.jpg"
     * @return
     *  eg:
     *  c942bc0eb7b1b781fd9fbac19613cd1f
     */
    public static String getPicId(String url) {
        Pattern compile = Pattern.compile("^.+(/(.*))\\.(jpg|jpeg|png|gif|bmp)$");
        Matcher matcher = compile.matcher(url);
        if (matcher.matches()) {
            String id = matcher.group(2);
        }
        return "";
    }

    public static void main(String[] args) {
        String id = "{\"TemplateId\":\"mgyys_data_template\",\"TemplateInstanceId\":\"5f41de4723c446acd8d16da0a2c44f77\"}";
        // 这个}的转义,在java中可以去除，但是在android中不行。
        Pattern compile = Pattern.compile("^.*(?:TemplateInstanceId\":\")+(.*)\"\\}$");
        Matcher matcher = compile.matcher(id);
        if(matcher.matches()){
            String subId = matcher.group(1);
            System.out.println("subId="+subId);
        }
    }

}
