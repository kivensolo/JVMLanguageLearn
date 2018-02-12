package com.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Copyright(C) 2018, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2018/2/12 16:41 <br>
 * description: XXXXXXX <br>
 */
public class Stackdumper {

    static final CheckerThread mThread = new CheckerThread();
    static final StackWriter stackWriter = new StackWriter();
    private static FileWriter fileWriter;
    private static long num = 0L;
    public static void main(String[] args) {
        System.out.println("启动.....");
        String fileName = "log.txt";
        String path = "E:" + File.separator + "wz" + File.separator;
        File dir = new File(path);
        if (!dir.isDirectory()) {
            System.out.println("路径不存在，创建路径");
            dir.mkdirs();
        }
        File logFile = new File(path.concat(fileName));
        try {
            fileWriter = new FileWriter(logFile, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取堆栈信息
        new Thread() {
            @Override
            public void run() {
                System.out.println("启动堆栈信息获取.....");
                while (true) {
                    num++;
                    mThread.run();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        //定时输出堆栈信息

        new Thread(){
            @Override
            public void run() {
                System.out.println("启动栈信息输出检测模块.....");
                while (true){
                    if(num >= 100){
                        System.out.println("触发dump机制，输出数据到文件");
                        stackWriter.run();
                        num = 0L;
                    }
                }
            }
        }.start();

    }

    static class CheckerThread extends Thread {
        @Override
        public void run() {
            try {
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                fileWriter.append("------ Time:".concat(String.valueOf(System.currentTimeMillis()).concat("\n")));
                for (StackTraceElement s : stackTrace) {
                    fileWriter.append("       ".concat(s.toString()).concat("\n"));
                }
                fileWriter.append("\r");
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }

    static class StackWriter implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("刷新writer数据");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
