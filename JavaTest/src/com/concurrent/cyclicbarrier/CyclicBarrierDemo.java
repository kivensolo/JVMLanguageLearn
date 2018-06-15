package com.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * author: King.Z <br>
 * date:  2018/6/15 15:40 <br>
 * description: CyclicBarrierDemo <br>
 * TODO 
 */
public class CyclicBarrierDemo {
    //构造函数指定party个数，并且提供一个可选的Runnable，当barrier打开的时候被调用执行。
    static final CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
        @Override
        public void run() {
            //barrierAction
            System.out.println("A~~ Barrier is Opened!");
        }
    });

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            createTask(i);
            //try {
            //    Thread.sleep(2500);  //任何的等待线程都会导致Barrier抛出BrokenBarrierException
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
        }
        reset();
    }

    public static void createTask(final int idnex) {
        new Thread() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    System.out.println("addTask :" + idnex);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                //每调用一次await,操作都会阻塞，直到调用次数达到往构造函数中传递的次数。
                //可选的RUnnable就会执行，并且所有的阻塞操作也会PAss
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    };

    Thread threadDeleay = new Thread() {
        @Override
        public void run() {
            try {
                //超时时会抛出TimeoutException   抛出异常后Barrier状态就异常了，
                cyclicBarrier.await(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    };

    public static void reset() {
        //允许Barrier重用
        cyclicBarrier.reset();
        System.out.println("reset barrier.");
    }
}
