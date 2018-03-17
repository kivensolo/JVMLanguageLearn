package com.string;

public class StringTest {

    public static String join(CharSequence delimiter,String... tokens){
        return joined(delimiter,tokens);
    }
     public static String joined(CharSequence delimiter, Object[] tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token: tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(join("+",new String[]{"a","d","c","f"}));
    }
}
