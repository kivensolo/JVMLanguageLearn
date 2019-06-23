package com.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author King.Z
 * @version ${STUB}
 * @since 2019/6/23 18:13 <br>
 *     Boss ---> Leader do sth. [Boot Certification]
 *     Leader ---> Staff do sth.
 *     Boss wait.
 */
public class Boss_Leader_Staff {

    public static void main(String[] args) {
        System.out.println("Boss do work >>>> ");
        Boss boss = new Boss();
        String s = boss.doWork();
        System.out.println("Boss get result = " + s);
    }

    interface Ijob{
        String doWork();
    }

    static class Boss implements Ijob{
        @Override
        public String doWork() {
            System.out.println("    boss let leader do Work >>> ");
            return new Leader().doWork();
        }
    }

    static class Leader implements Ijob{

        @Override
        public String doWork() {
            System.out.println("    Leader doWork ..... ");
            ExecutorService executorService = Executors.newCachedThreadPool();
            String result = "";
            try {
               System.out.println("     Leader submit a CallableTask ....");
                Future<String> future = executorService.submit(new StaffCallableTask());
                result = future.get(); //Block this
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("    CallableTask done....  result = " + result);
            return result;
        }
    }

    static class Staff implements Ijob{

        @Override
        public String doWork() {
            //Staff need time to do Something.
            System.out.println("        Staff doing work ----- Time-consuming work ~~~");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("        Staff do work done.");
            return "Sucess!!";
        }
    }

    static class StaffCallableTask implements Callable<String> {
        public StaffCallableTask() {
        }

        @Override
        public String call() {
            return new Staff().doWork();
        }
    }
}
