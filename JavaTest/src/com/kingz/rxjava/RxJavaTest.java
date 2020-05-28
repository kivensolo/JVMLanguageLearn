package com.kingz.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author: King.Z
 * @since: 2020/3/5 21:34 <br>
 * @desc: rxJava基础学习.
 * RxJava基于事件流的链式调用,
 * <p>
 * 在控制访问请求顺序、并发请求、失败重试等方面很方便。
 */
public class RxJavaTest {
    public static void main(String[] args) {
        //Operators operators = new Operators();
        //operators.just();
        //operators.Interval();

        CombineOperators cOperators = new CombineOperators();
        //cOperators.concat();
        cOperators.merge();
        //cOperators.zip();
    }
}

/**
 * 组合操作符
 */
class CombineOperators {

    /**
     * 串联组合 然后按照之前发送顺序发送事件。
     * 最多只可以发送4个事件
     */
    void concat() {
        Observable<Integer> o_1 = Observable.just(1, 2);
        Observable<Integer> o_2 = Observable.just(3, 4);
        Observable<Integer> o_3 = Observable.just(5, 6);
        Observable<Integer> o_4 = Observable.just(7, 8);
        Observable<Integer> o_5 = Observable.create(emitter -> {
            emitter.onNext(10086);
            emitter.onError(new NumberFormatException("异常测试"));
        });

        Observable.concat(o_1, o_2, o_3, o_4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("================onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        System.out.println("concatArray---->");
        Observable.concatArray(o_1, o_2, o_5, o_3, o_4).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("concatArray ================onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("concatArray ================Throwable:" + e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        });
    }


    /**
     * FixME  merge不生效
     */
    void merge() {
        Observable<String> observable_A = Observable.interval(1, TimeUnit.SECONDS).map(value -> "A_" + value);
        Observable<String> observable_B = Observable.interval(1, TimeUnit.SECONDS).map(aLong -> "B_" + aLong);

        //Observable<Integer> odds = Observable.just(1, 3, 5);
        //Observable<Integer> evens = Observable.just(2, 4, 6);
        Observable.merge(observable_A,observable_B)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("================onNext:" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }


    void zip() {
        Observable<String> emit5 = Observable.intervalRange(1,
                5, 1, 1, TimeUnit.SECONDS)
                .map(value -> {
                    String s1 = "A" + value;
                    System.out.println("===================A 发送的事件 " + s1);
                    return s1;
                });

        Observable<String> emit6 = Observable.intervalRange(1,
                6, 1, 1, TimeUnit.SECONDS)
                .map(value -> {
                    String s2 = "B" + value;
                    System.out.println("===================B 发送的事件 " + s2);
                    return s2;
                });


        Observable.zip(emit5, emit6, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s1, String s2) throws Exception {
                return s1 + s2;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("===================onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("===================onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("===================onError ");
            }

            @Override
            public void onComplete() {
                System.out.println("===================onComplete ");
            }
        });

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
    public void Interval() {
        Observable.interval(0, 2000, TimeUnit.MILLISECONDS)
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