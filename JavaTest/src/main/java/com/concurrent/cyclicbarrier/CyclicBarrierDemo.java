package com.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * author: King.Z <br>
 * date:  2018/6/15 15:40 <br>
 * description: CyclicBarrierDemo <br>
 * CyclicBarrier 控制任务一起启动，
 * 选手准备，选手到齐后放开栅栏。
 */
public class CyclicBarrierDemo {
    //构造函数指定party个数，并且提供一个可选的Runnable，当barrier打开的时候被调用执行。
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
        System.out.println("Barrier Open !!! go !");         //barrierAction
    });

    public static void main(String[] args) {
        for (int i = 0; i <= 3; i++) {
            createTask(i);
        }
        reset();
    }

    private static void createTask(final int index) {
        new Thread(() -> {
            try {
                //每调用一次await,操作都会阻塞，直到调用次数达到往构造函数中传递的次数。
                //可选的Runnable就会执行，并且所有的阻塞操作都会Pass
                cyclicBarrier.await();

                //超时时会抛出TimeoutException   抛出异常后Barrier状态就异常了，
                //cyclicBarrier.await(1000, TimeUnit.MILLISECONDS);'
                System.out.println("Task run :" + index);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void reset() {
        //允许Barrier重用
        cyclicBarrier.reset();
        System.out.println("reset barrier.");
    }
}
