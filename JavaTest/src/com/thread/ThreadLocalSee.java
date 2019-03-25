package com.thread;

/**
 * author: King.Z <br>
 * date:  2019/3/17 13:52 <br>
 * desc: ThreadLocal简单使用
 */
public class ThreadLocalSee {

    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>() {
        @Override
        protected Object initialValue() {
            System.out.println("initialValue(） return null");
            return null;
        }
    };

    public static void main(String[] args) {
        CustomThread thread_a = new CustomThread(new TASK_A());
        CustomThread thread_b = new CustomThread(new TASK_B());
        thread_a.start();
        thread_b.start();
    }

    static class CustomThread extends Thread {

        public CustomThread(Runnable target) {
            super(target);
        }

        @Override
        public void run() {
            super.run();
        }
    }

    static class TASK_A implements Runnable {
        String name = "Task_A";

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                //当前threadLocal是否存有数据
                if (null == ThreadLocalSee.threadLocal.get()) {
                    System.out.println("Task_A get is null ,to Set default value 0.");
                    ThreadLocalSee.threadLocal.set(0);
                    System.out.println(name + ": 0");
                } else {
                    int num = (Integer) ThreadLocalSee.threadLocal.get();
                    ThreadLocalSee.threadLocal.set(num + 1);
                    System.out.println("get()  " + name + ": " + ThreadLocalSee.threadLocal.get());
                    if (i == 3) {
                        System.out.println("Task_A  local value is 3, remove values.");
                        ThreadLocalSee.threadLocal.remove();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TASK_B implements Runnable {
        String name = "task_b";

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                //当前threadLocal是否存有数据
                if (null == ThreadLocalSee.threadLocal.get()) {
                        System.out.println("task_b get is null ,to Set default value zero.");
                    ThreadLocalSee.threadLocal.set("zero");
                    System.out.println( name + ": zero");
                } else {
                    String str = (String) ThreadLocalSee.threadLocal.get();
                    ThreadLocalSee.threadLocal.set(str + 1);
                    System.out.println("get()  " + name + ": " + ThreadLocalSee.threadLocal.get());
                }
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
