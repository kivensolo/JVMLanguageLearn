package com.synchronizedTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/17 17:34
 * description:
 */
public class synchroRightDemo implements Runnable {

    public static void main(String args[]) {
        synchroRightDemo demo = new synchroRightDemo();
        new Thread(demo, "a").start();
        new Thread(demo, "b").start();
        new Thread(demo, "c").start();

        try {
            Thread.sleep(1000 * 7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockDemo lockDemo = new LockDemo();
        new Thread(lockDemo, "aaaa").start();
        new Thread(lockDemo, "bbbbb").start();
        new Thread(lockDemo, "ccccc").start();
    }

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


    static class LockDemo implements Runnable {
        private int ticket = 20;
        private final ReentrantLock lock = new ReentrantLock(); //可重入锁

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    if (this.ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "号窗口卖出：" + this.ticket-- + "号票");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
