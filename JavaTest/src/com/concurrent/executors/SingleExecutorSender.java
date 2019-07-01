package com.concurrent.executors;

import java.util.concurrent.*;

/**
 * description：单例线程池,用于处理分发Runnable和Callable任务
 */
public class SingleExecutorSender {
    private static SingleExecutorSender sExecutorServiceHelper;
    private ExecutorService mSingleThreadExecutor;

    public static synchronized SingleExecutorSender getInstance() {
        if (sExecutorServiceHelper == null) {
            sExecutorServiceHelper = new SingleExecutorSender();
        }
        return sExecutorServiceHelper;
    }

    public synchronized void execute(Runnable runnable) {
        if (mSingleThreadExecutor == null) {
            mSingleThreadExecutor = Executors.newCachedThreadPool();
        }
        mSingleThreadExecutor.execute(runnable);
    }

    public synchronized void execute(Callable callable) {
        if (mSingleThreadExecutor == null) {
            mSingleThreadExecutor = Executors.newCachedThreadPool();
        }
        Future mSubmit = mSingleThreadExecutor.submit(callable);
        showResult(mSubmit);
    }

    private void showResult(Future<?> mSubmit) {
        try {
            System.out.println("SingleExecutorSender :" + mSubmit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
