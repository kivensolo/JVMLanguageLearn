package com.concurrent.future;

/**
 * author: King.Z <br>
 * date:  2018/6/15 15:11 <br>
 * description: Future obj are returned on submit to ExeCutorService
 * or can be created by constructing a FutureTask.
 *
 * The Future.get() method blocks untils some result is available.
 * <br>
 */
public class FutureDemo {

    //Send a Future task:
    /*
       [启动]
        someCallable  实现了Callable接口 里面的call()方法  和run()方法相同
        final Future future =
                Executors.newCachedThreadPool()
                 .submit(someCallable);
        //Or

        FutureTask<Callable> future = new FutureTask<Callable>(someCallable);
        Thread thread = new Thread(futureTask)；
        thread.start();

       [结果获取]
       try{
        Object result = future.get();
       }catch(InterruptedExecption e){ ... }
        get()方法如果没执行，则task结束后随时调用都可以立即获取返回结果。若在执行完毕之前调用get
        则会阻塞 直到task执行完成。

     */

}
