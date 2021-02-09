package com.io.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: King.Z <br>
 * date:  2018/2/12 16:41 <br>
 * description: XXXXXXX <br>
 */
public class StackTest {

    public static void main(String[] args) {
        ExecutorService mSingleThreadExecutor;

        System.out.println("启动.....");
        final List<Integer> collectArea = new ArrayList<>();
        final List<Integer> catchedArea = new ArrayList<>();
        //Collect Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                for (;;) {
                    collectArea.add(index++);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        StackMinitor.getInstance().start();
        mSingleThreadExecutor = Executors.newSingleThreadExecutor();
        mSingleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int temp = 0;
                for (;;) {
                    if(temp % 5 == 0){
                        catchedArea.addAll(collectArea);
                        StackMinitor.getInstance().mStackDumper.dump();
                    }
                    temp++;
                }
            }
        });
    }
    //public static void main(String[] args) {
    //    System.out.println("result="+ getCurrentTimeFormat());
    //}
    //   public static String getTimeFormat(DateFormat df){
    //    return df.format(new Date());
    //}
    //
    //public static String createFileName(){
    //    return getTimeFormat(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss:ms")).concat(".txt");
    //}
    //
    //
    //public static String getCurrentTimeFormat(){
    //    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:ms");
    //    return df.format(System.currentTimeMillis());
    //}
}
