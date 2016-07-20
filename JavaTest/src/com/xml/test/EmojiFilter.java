package com.xml.test;

import org.json.JSONObject;

import java.text.ParseException;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/7/15 23:27 <br>
 * description: Emoji表情过滤 <br>
 */
public class EmojiFilter {

    public static void main(String[] args) throws ParseException {
        CharSequence charSequence = "\u66fe\u7ecf\u6ca7\u6d77\u96be\u4e3a\u6c34\ud83d\ude00";

        //Json数据
        String src = "{\"result\":{\"state\":300000,\"sub_state\":300001,\"reason\":\"TOKEN\\u8ba4\\u8bc1\\u6210\\u529f\"},\"auth\":{\"web_token\":\"1bb88d9de60f98df1197306bae3ea12b\",\"expires_in\":86073,\"valid_time\":\"2016-07-16T23:47:31+0800\",\"refresh_time\":\"1907\"},\"user\":{\"id\":\"91468462948\",\"name\":\"\\u66fe\\u7ecf\\u6ca7\\u6d77\\u96be\\u4e3a\\u6c34\\ud83d\\ude00\",\"rank\":\"1\",\"email\":\"\",\"telephone\":\"\",\"addr\":\"\\u4e2d\\u56fd\\u56db\\u5ddd\\u6210\\u90fd\",\"age\":\"\",\"sex\":\"\",\"occupation\":\"\",\"device_id\":\"3C-DA-2A-B1-49-B7\",\"headimgurl\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/XG9eicgyIkNDNKjcsfGq7ewGRpumsrmcbyJvZNsfyFiaIGpTm0uJq8D23m6LbicgL0g2lGxDP0yGbdRfkJgTgllN31eM7nOCGLW\\/0\",\"first_use_time\":\"2016-07-15 10:32:29\",\"attribution\":\"\\u6210\\u90fd\",\"user_from\":\"3\",\"user_level\":0,\"user_growth_value\":\"\",\"user_level_begin_time\":\"\",\"user_level_end_time\":\"\",\"boss_top_box_id\":\"\",\"user_is_category\":\"\",\"boss_area_code\":\"\",\"boss_area_bind\":\"NO\",\"area_code\":\"\",\"login_id\":\"korkmac3CDA2AB149B7\"},\"client_ip\":\"182.138.101.47\"}";
        JSONObject jsonObject = new JSONObject(src);
        JSONObject userObj = jsonObject.optJSONObject("user");
        String result = userObj.optString("name");
        System.out.println("result:"+result+"]");

        //String nodeName = String.valueOf(charSequence);
        //nodeName = replaceInvaldateCharacter(nodeName);
        result = result.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
        System.out.println("charSequence："+charSequence+"]");
        System.out.println("字符串---->"+result+"]");
    }
}
