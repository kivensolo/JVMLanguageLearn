package com.kingz.sys;

import java.io.File;
import java.util.Map;

/**
 * author: King.Z <br>
 * date:  2019/1/14 11:29 <br>
 * description: 获取系统环境变量 <br>
 */
public class EnvironmentParms {

    public static void main(String[] args) {
        //getEnv();
        String join = join(File.separator, new String[]{"a", "b",""});
        System.out.println(join);
    }

    public static void getEnv(){
        Map<String, String> environment = System.getenv();
        for(Map.Entry<String,String> v : environment.entrySet()){
            System.out.println(v.getKey());
        }
    }

    public static String join(String sep, String[] container) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Object s : container) {
			if (stringBuilder.length() > 0) {
				stringBuilder.append(sep);
			}
			stringBuilder.append(s);
		}
		return stringBuilder.toString();
	}

}
