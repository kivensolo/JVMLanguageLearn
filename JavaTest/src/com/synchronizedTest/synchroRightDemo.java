package com.synchronizedTest;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/17 17:34
 * description:
 */
public class synchroRightDemo implements Runnable {
    //若单独加transient修饰符，还是没有用的
    private int ticket = 20;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if (this.ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "号窗口卖出：" + this.ticket-- + "号票");
                }
            }
        }
    }

    public static void main(String args[]) {
        synchroRightDemo demo = new synchroRightDemo();
        new Thread(demo, "a").start();
        new Thread(demo, "b").start();
        new Thread(demo, "c").start();
    }
}
