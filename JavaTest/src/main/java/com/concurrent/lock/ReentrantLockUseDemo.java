package com.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock使用
 * https://www.cnblogs.com/fsmly/p/10703804.html
 */
public class ReentrantLockUseDemo {
    private long tickeNums = 100 * 1000;
    //可以是自己实现Lock接口的实现类，也可以是jdk提供的同步组件，比如重入锁ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    void m1() {
        lock.lock(); // 加锁
        try {
            for (int i = 0; i < 4; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1() method for i=" + i +", " + Thread.currentThread().getName());
            }
            m2(); //在释放锁之前，调用m2方法
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2() method：" + Thread.currentThread().getName());
        lock.unlock();
    }


    /**
     * 获取ticket，总数减少1
     */
    public void getTicket() {
        lock.lock(); //阻塞式的获取锁
        try {
            tickeNums--;
        } finally {
            //放在finally代码块中，保证锁一定会被释放
            lock.unlock();
        }
    }

    public long getRemain() {
        lock.lock();
        try {
            return tickeNums;
        } finally {
            //放在finally代码块中，保证锁一定会被释放
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantLockUseDemo t = new ReentrantLockUseDemo();
        Thread thread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread-A run....");
                t.m1();
            }
        });
        thread_1.setName("Thread-A");
        thread_1.start();

         Thread thread_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread-B run....");
                t.m2();
            }
        });
        thread_2.setName("Thread-B");
        thread_2.start();

    }

}