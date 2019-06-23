package com.concurrent.executors;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * @author King.Z
 * @version ${STUB}
 * @since 2019/6/23 18:58 <br>
 *     顺序执行队列中的可执行任务
 */
public class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    protected synchronized void addTask(Runnable runnable){
        tasks.add(runnable);
    }

    @Override
    public synchronized void execute(final Runnable r) {
        //从队列中依次提取可执行的Runnable对象
        tasks.offer(() -> {
            try {
                r.run();
            } finally {
                //执行完成后进行下一个任务的执行
                scheduleNext();
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}