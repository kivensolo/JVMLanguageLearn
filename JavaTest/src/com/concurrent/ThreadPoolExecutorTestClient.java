package com.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 探究线程池核心线程大小和线程池总大小的本质区别
 */
public class ThreadPoolExecutorTestClient {
    /**
     * The core size of the thread pool for asynchronous loaders.
     */
    private static final int THREAD_POOL_CORE_SIZE = 1;

     /**
     * The max size of the thread pool for asynchronous loaders.
     */
    private static final int THREAD_POOL_MAX_SIZE = 2;

    /**
     * The thread pool keep alive time.
     */
    private static final long THREAD_POOL_KEEP_ALIVE_TIME = 10000L;

    public static void main(String[] args) {
        //int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        //ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2,
        //        5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        ExecutorService executorService = new ThreadPoolExecutor(THREAD_POOL_CORE_SIZE,
                THREAD_POOL_MAX_SIZE,
                THREAD_POOL_KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));
        Runnable runnableA = () -> {
            while (true) {
                System.out.println("runing A.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnableB = () -> System.out.println("runing B.");
        Runnable runnableC = () -> System.out.println("runing C.");
        executorService.execute(runnableA);
        executorService.execute(runnableB);
        executorService.execute(runnableC);
        // ----------------------输出结果:
        //  runing A.
        //  runing C.
        //  runing B.
    }
}
