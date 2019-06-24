package com.concurrent.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * author: King.Z <br>
 * date:  2018/6/15 15:01 <br>
 * description: Executor Utils <br>
 */
public class ExecutorUtils {
    public static final int FIX_POOL_SIZE = 4;
    //创建固定数量的线程池后  里面空闲的线程就是4个  且最多也只有四个同时工作
    static final Executor fixedExecutor = Executors.newFixedThreadPool(FIX_POOL_SIZE);

    public static void exeFixedWork(){
        //User the executor to execute Runnabel
        fixedExecutor.execute(() -> {
            //Do work with Runnable
        });
    }
}
