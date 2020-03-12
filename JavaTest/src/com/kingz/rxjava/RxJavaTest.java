package com.kingz.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author: King.Z
 * @since: 2020/3/5 21:34 <br>
 * @desc: rxJava基础学习.
 *  RxJava基于事件流的链式调用,
 *
 *  在控制访问请求顺序、并发请求、失败重试等方面很方便。
 */
public class RxJavaTest {
    public static void main(String[] args) {
        Operators operators = new Operators();
        //operators.just();
        operators.Interval();
    }
}
class Operators {
    Disposable disposable;

    // 验证各个操作符效果
    public void just() {
        //Observable observable = Observable.create((ObservableOnSubscribe) emitter -> {
        //    System.out.println("subscribe");
        //});
        Observable.just(1, 3, 5, 7)
          .map(data -> {
                    System.out.println("apply(): 2 * " + data);
                    return data * 2;
                })
          .filter(data -> {
            System.out.println("filter: 10 >= " + data);
            return data <= 10;
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("-----> onSubscribe()  Start emits itmes!");
                disposable = d;
            }

            @Override
            public void onNext(Integer data) { // 只会在数据满足条件时毁掉onNext
                System.out.println("received onNext:" + data + "\n");
                if (data == 14000000) {
                    System.out.println("Something happend, disposable.dispose()");
                    disposable.dispose(); // 直接中断，收不到事件发送了。
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete() <-------- ");
            }
        });
    }

    //  -----> onSubscribe()  Start emits itmes!
    //  apply(): 2 * 1
    //  filter: 10 >= 2
    //  received onNext:2
    //
    //  apply(): 2 * 3
    //  filter: 10 >= 6
    //  received onNext:6
    //
    //  apply(): 2 * 5
    //  filter: 10 >= 10
    //  received onNext:10
    //
    //  apply(): 2 * 7
    //  filter: 10 >= 14
    //  onComplete() <--------


    /**
     * 创建一个可以按照给定时间间隔来不间断发送数字序列的可观察对象
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void Interval(){
        Observable.interval(0,2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                // Consumer
                .subscribe(time -> System.out.println("Consumer accept:" + time));
                // Observer
                //.subscribe(new Observer<Long>() {
                //    @Override
                //    public void onSubscribe(Disposable disposable) {
                //        System.out.println("onSubscribe()");
                //    }
                //
                //    @Override
                //    public void onNext(Long aLong) {
                //        System.out.println("onNext() " + aLong);
                //
                //    }
                //
                //    @Override
                //    public void onError(Throwable throwable) {
                //
                //    }
                //
                //    @Override
                //    public void onComplete() {
                //        System.out.println("onComplete() ");
                //
                //    }
                //});

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}