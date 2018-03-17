package com.concurrent;

/**
 * Copyright(C) 2018, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2018/3/ 17:24 <br>
 * description: XXXXXXX <br>
 */
public class P21_01 implements Runnable{
    public P21_01() {
        System.out.println("boot start.");
    }

    @Override
    public void run() {
        int times = 3;
        while(times >= 0) {
            System.out.println("hello p21_01 i="+times);
            Thread.yield();
            times--;
        }
    }
}
