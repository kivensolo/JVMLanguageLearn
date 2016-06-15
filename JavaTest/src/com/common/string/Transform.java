package com.common.string;

import java.util.Arrays;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/6/12 17:25 <br>
 * description: XXXXXXX <br>
 */
public class Transform {
    public static void main(String[] args) {

    }

    /** String 转 byte */
    private static void str2Byte(String str){
        byte[] bs = str.getBytes();
        System.out.println(Arrays.toString(bs));
    }

    /** byte 转 String */
    private static void byte2str(byte[] bs){
         String str2 = new String(bs);
        System.out.println(Arrays.toString(bs));
    }
}
