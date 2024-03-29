package com.kingz.utils;


/**
 * 字符串处理类
 * @author 6a209
 * Feb 16, 2013
 */
public class TextUtils{

	public static boolean isEmpty(String str){
		if(str != null && str.length() > 0){
			return false;
		}
		return true;
	}

	public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }


	public static String makeHtmlStr(String text, String color){
		return "<font color=\"" + color + "\">" + text + "</font>";
	}

	public static String makeHtmlStr(String text, int color){
		String strColor = String.valueOf(color);
		return makeHtmlStr(text, strColor);
	}

    public static String getUnNullString(String content) {
        if (null == content) {
            return "";
        }
        return content;
    }
}