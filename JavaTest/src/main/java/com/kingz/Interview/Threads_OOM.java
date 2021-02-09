package com.kingz.Interview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: King.Z <br>
 * date:  2018/10/9 9:45 <br>
 * description: 美团面试真题：一个进程有3个线程，如果一个线程抛出OOM，其他两个线程还能运行么? <br>
 *     多线程中栈与堆是公有的还是私有的？
 *     <b>在多线程环境下，每个线程拥有一个栈和一个程序计数器。
 *     栈和程序计数器用来保存线程的执行历史和线程的执行状态，是线程私有的资源。
 *     其他的资源（比如堆、地址空间、全局变量）是由同一个进程内的多个线程共享。<b/>
 */
public class Threads_OOM {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<byte[]> list = new ArrayList<byte[]>();
                while (true) {
                    System.out.println(new Date().toString() + Thread.currentThread() + ">>>");
                    byte[] b = new byte[1024 * 1024 * 5];
                    list.add(b);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"OOM-Thread").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(new Date().toString() + Thread.currentThread() + ">>>");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Normal-Thread").start();

    }
}
