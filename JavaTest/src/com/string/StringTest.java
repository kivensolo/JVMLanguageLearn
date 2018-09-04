package com.string;

import com.string.replace.LineMatcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StringTest {

    public static String readAndModify(String filePath, String formate,
    		String content, String oldValue) {
    	String fileContents = null;
    	try {
    		fileContents = LineMatcher.instance().modifyContents(new FileInputStream(new File(filePath)),
					null, formate, oldValue, content);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

        return fileContents;
    }
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

    public static String val = "             s   com.starcor.hunan,\n" +
            "        com.starcor.xinjiang   s";
    public static void main(String[] args) {
        String extra = "\\n|\\r|\\t|";
        val =  val.trim();
        val = val.replaceAll(extra, "");
        System.out.println(val);
    }
}



