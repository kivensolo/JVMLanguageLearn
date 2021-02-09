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

}
