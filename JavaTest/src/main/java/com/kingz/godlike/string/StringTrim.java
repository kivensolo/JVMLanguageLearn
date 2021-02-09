package com.kingz.godlike.string;

/**
 * author: King.Z <br>
 * date:  2017/7/26 21:54 <br>
 * description: 清理文字中的转义字符 <br>
 */
public class StringTrim {
     public static String val ="com.starcor.hunan,\n" +
            "        com.starcor.xinjiang    ";
    public static void main(String[] args) {
        String extra = "\\n|\\r|\\s|\\t|";
        val =  val.trim();
        val = val.replaceAll(extra, "");
        System.out.println(val);
    }
}
