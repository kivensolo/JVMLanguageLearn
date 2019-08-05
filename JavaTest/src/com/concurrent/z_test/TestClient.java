package com.concurrent.z_test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestClient {
    public static void main(String[] args) {
        // 2.测试
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2,
                5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        executorService.execute(()->{
            System.out.println("Start do work");
        });
    }
}
