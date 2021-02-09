package com.hello;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhengze {
    public static String styleValues = "special_id=test005444#style=1;\n" +
            " special_id=test004#style=2;\n" +
            " special_id=test003#style=1;\n" +
            " special_id=test002#style=1;\n" +
            " special_id=test003231#style=2;";
    public static HashMap<String, String> styleMap = new HashMap<String, String>();

    public static String getStyleById(String id) {
        return styleMap.get(id) == null ? "" : styleMap.get(id);
    }

    public static void main(String[] args) {

        String[] specials = styleValues.split(";");
        for (String str:specials){
            str = str.trim();
            Pattern styleP = Pattern.compile("^.*=.*#.*=.*$",Pattern.CASE_INSENSITIVE);
            Matcher matcher = styleP.matcher(str);
            if(matcher.matches()){
                String id = str.substring(str.indexOf("special_id=") + 11,str.indexOf("#"));
                String style = str.substring(str.indexOf("style=") + 6 );
                System.out.println("id="+id + "  style = " + style);
            }else{
                System.out.println("不匹配 =.#.=");
            }
        }

    }


}
