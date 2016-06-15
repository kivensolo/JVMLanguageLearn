package com.kingz.godlike;

import java.security.MessageDigest;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/6/14 17:36 <br>
 * description: XXXXXXX <br>
 */
public class HwDES {


    public static void main(String[] args) {
        String original = "123456";//原始明文密码
        String algorithmParm = "99991231";
        try {
            byte[] id = original.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(id);
            md.update(algorithmParm.getBytes());
            byte[] buffer = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < buffer.length; i++) {
                sb.append(Integer.toHexString((int) buffer[i] & 0xff));
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
