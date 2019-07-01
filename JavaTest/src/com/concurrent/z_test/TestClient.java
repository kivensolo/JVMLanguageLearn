package com.concurrent.z_test;

import com.concurrent.executors.SingleExecutorSender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestClient {
    public static void main(String[] args) {
        //_testRunnableSleep();

        // 2.测试
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2,
                5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        executorService.execute(()->{
            System.out.println("Start do work");
        });
    }

    /**
     * 测试用Ececutor执行Runnable任务的时候，在子线程中使用Thread.sleep方法会不会使得子线程睡眠
     */
    private static void _testRunnableSleep() {
        SingleExecutorSender.getInstance().execute(() -> {
            System.out.println("Start do work " + Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis());
            System.out.println("End Work.");
        });
    }
}
