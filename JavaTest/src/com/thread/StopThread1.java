package com.thread;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/6/15 16:33 <br>
 * description:  如何正确的中断一个正在运行的线程 <br>
 */

//Thread.interrupt()并不能成功停止线程
public class StopThread1 extends Thread {
    boolean stop = false;

    public static void main(String args[]) throws Exception {
        StopThread1 thread = new StopThread1();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");
        //System.exit(0);
    }

    public void run() {
        while (!stop) {
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000)) {
            }
        }
        System.out.println("Thread exiting under request...");
    }
}


//真正地中断一个线程，中断线程最好的，最受推荐的方式是，使用共享变量（shared variable）发出信号
class Example2 extends Thread {
    volatile boolean stop = false;

    public static void main(String args[]) throws Exception {
        Example2 thread = new Example2();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");
        thread.stop = true;
        Thread.sleep(3000);
        System.out.println("Stopping application...");
        //System.exit( 0 );
    }

    public void run() {
        while (!stop) {
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000) && (!stop)) {
            }
        }
        System.out.println("Thread exiting under request...");
    }
}
