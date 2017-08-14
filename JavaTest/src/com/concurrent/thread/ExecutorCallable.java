package com.concurrent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Copyright(C) 2017, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/8/14 21:45 <br>
 * description: Executor执行Callable任务 <br>
 * Runnable和Callable两者都可以被ExecutorService执行，但是Runnable任务没有返回值，而Callable任务有返回值。
 * 并且Callable的call()方法只能通过ExecutorService的submit(Callable<T> task) 方法来执行，
 * 并且返回一个 Future，是表示任务等待完成的 Future。
 * <p>
 * Callable接口类似于Runnable，两者都是为那些其实例可能被另一个线程执行的类设计的。
 * 但是 Runnable 不会返回结果，并且无法抛出经过检查的异常而Callable又返回结果，
 * 而且当获取返回结果时可能会抛出异常。Callable中的call()方法类似Runnable的run()方法，
 * 区别同样是有返回值，后者没有。
 */
public class ExecutorCallable {
    public static void main(String[] args) {
        CallableTest.start();
    }
}

class CallableTest {
    static void start() {
        ExecutorService executorService  = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<Future<String>>();
        for (int i = 0; i < 100; i++) {
            Future<String> future = executorService .submit(new TaskWithResult(i));
            resultList.add(future);
        }
        for (Future<String> fs : resultList) {
            try {
                //blocking result
                while (!fs.isDone()){
                    System.out.println(" wait....." + fs.get());
                }
                System.out.println(fs.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
    }
}

class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，
     * 则该方法自动在一个线程上执行
     */
    @Override
    public String call() throws Exception {
        System.out.println("开始执行 id:" + id + "    " + Thread.currentThread().getName());
        return "Task finish. id = " + id + "    " + Thread.currentThread().getName();
    }
}