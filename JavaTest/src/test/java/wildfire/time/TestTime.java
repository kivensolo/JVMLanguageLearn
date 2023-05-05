package wildfire.time;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestTime {


    /**
     * 日期格式化器
     * 这里非静态也可以，主要是让多个线程共享这个 SimpleDateFormat 对象
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 使用线程池结合Java并发包中的CountDownLatch类和Semaphore类来重现SimpleDateFormat的线程安全问题。
     * 1 CountDownLatch 类可以使一个线程等待其他线程各自执行完毕后再执行。
     * 2 Semaphore 类可以理解为一个计数信号量，必须由获取它的线程释放，经常用来限制访问某些资源的线程数量，例如限流等。
     */
    @Test
    public void testSimpleFormatUnsafe() {
        // 总任务数
        int EXECUTE_COUNT = 100;
        // 同时执行的任务数的上限
        int THREAD_COUNT = 20;

        // 信号量，类似于 PV 操作的信号量
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);

        /*
         *  CachedThreadPool 线程池：
         *  构造方法：
         *  new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *                           60L, TimeUnit.SECONDS,
         *                           new SynchronousQueue<Runnable>());
         *  任务执行的流程：
         *  1 因为没有核心线程，所以，会直接向 SynchronousQueue 中提交任务；
         *  2 如果线程池中有空闲线程，就去取出任务执行；如果没有空闲线程，就新建一个；
         *  3 执行完任务的线程有 60 秒生存时间，如果在这个时间内可以接到新任务，就可以继续活下去，否则就拜拜；
         *  4 由于空闲 60 秒的线程会被终止，长时间保持空闲的 CachedThreadPool 不会占用任何资源。
         *
         *  SynchronousQueue 阻塞队列：
         *  CachedThreadPool 使用的阻塞队列是 SynchronousQueue。
         *  SynchronousQueue 是一个内部只能包含一个元素的队列。
         *  插入元素到队列的线程被阻塞，直到另一个线程从队列中获取了队列中存储的元素。
         *  同样，如果线程尝试获取元素并且当前不存在任何元素，则该线程将被阻塞，直到线程将元素插入队列。
         *  用途：
         *
         *  CachedThreadPool 用于并发执行大量短期的小任务，或者是负载较轻的服务器。
         */
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executor.execute(() -> {
                try {
                    // 限制最多同时只能有 20 个线程并发执行
                    semaphore.acquire();
                    try {
                        // String 字符串 解析为 Date对象
                        sdf.parse(LocalDate.now().toString());
                    } catch (ParseException e) {
                        System.out.println("线程 " + Thread.currentThread().getName() + "格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    // 释放信号量
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                //
                countDownLatch.countDown();
            });
        }
        try {
            // main 线程阻塞，阻塞到所有的线程执行完成为止
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 等待所有任务执行结束，再关闭线程池
            executor.shutdown();
        }
        System.out.println("所有日期格式化完成");
    }

    @Test
    public void SimpleDateFormatTestV2() {
        // 初始化每个线程的时间
        List<Date> list = new ArrayList<>();
        // 每个线程对应处理的Date对象,后续会开启10个线程
        Date date1 = new Date(2013 - 1900, Calendar.JANUARY, 1);
        Date date2 = new Date(2014 - 1900, Calendar.JANUARY, 1);
        Date date3 = new Date(2015 - 1900, Calendar.JANUARY, 1);
        Date date4 = new Date(2016 - 1900, Calendar.JANUARY, 1);
        Date date5 = new Date(2017 - 1900, Calendar.JANUARY, 1);
        Date date6 = new Date(2018 - 1900, Calendar.JANUARY, 1);
        Date date7 = new Date(2019 - 1900, Calendar.JANUARY, 1);
        Date date8 = new Date(2020 - 1900, Calendar.JANUARY, 1);
        Date date9 = new Date(2021 - 1900, Calendar.JANUARY, 1);
        Date date10 = new Date(2022 - 1900, Calendar.JANUARY, 1);
        list.add(date1);
        list.add(date2);
        list.add(date3);
        list.add(date4);
        list.add(date5);
        list.add(date6);
        list.add(date7);
        list.add(date8);
        list.add(date9);
        list.add(date10);
        ExecutorService service = Executors.newFixedThreadPool(10);
        try {
            for (int i = 0; i < list.size(); i++) {
                final int index = i;
                service.execute(() -> {
                    String name = Thread.currentThread().getName();
                    Date date = list.get(index);
                    String dateStr = date.toString();
                    // 格式化 日期
                    System.out.println("[" + name + "]: ====> 格式化前" + dateStr);
                    String res = sdf.format(date);
                    System.out.println("[" + name + "]:       格式化后" + res);
                });
            }
        } finally {
            service.shutdown();
        }
    }

}
