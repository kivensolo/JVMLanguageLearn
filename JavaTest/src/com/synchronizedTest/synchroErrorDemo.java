package com.synchronizedTest;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/17 17:32
 * description: 资源同步的错误示例
 */
public class synchroErrorDemo implements Runnable {
    private int ticket = 10;
    public synchroErrorDemo() {
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (this.ticket > 0) {
                //休眠1s秒中，为了使效果更明显，否则可能出不了效果
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "号窗口卖出：" + this.ticket-- + "号票");
            }

        }
    }

    public static void main(String args[]) {
        synchroErrorDemo demo = new synchroErrorDemo();
        //基于火车票创建三个窗口
        new Thread(demo, "a").start();
        new Thread(demo, "b").start();
        new Thread(demo, "c").start();
    }
    //结果会出现有不同窗口卖出同样的票，且会卖出负的票

}
