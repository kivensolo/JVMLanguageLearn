package com.concurrent;

/**
 * Copyright(C) 2018, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2018/3/17 17:27 <br>
 * description: XXXXXXX <br>
 */
public class Practice {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new P21_01()).start();
        }
        System.out.println("END");
    }
}
