package com.kingz.godlike.concurrent;

/**
 * author: King.Z <br>
 * date:  2017/9/3 14:37 <br>
 * description: XXXXXXX <br>
 */
public class P21_1 {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Thread th1 = new Thread(new Printer());
            th1.setName(i+"");
            th1.start();
        }

    }

    static class Printer implements Runnable {
        public Printer() {
            System.out.println("start print");
        }

        int times = 0;
        @Override
        public void run() {
            while (times <= 3){
                System.out.println("hello:"+times);
                Thread.yield();
                times++;
            }
            System.out.println("print stop:");
        }
    }
}
