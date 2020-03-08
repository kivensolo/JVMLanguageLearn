package com.kingz.rx;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: King.Z
 * @since: 2020/3/5 21:34 <br>
 * @desc: rxJava学习.
 * RxJava基于事件流的链式调用,
 * 在控制访问请求顺序、并发请求、失败重试等方面很方便。
 */
public class RxJavaTest {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3);
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("=================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("=================onNext " + integer);

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("=================onError");

            }

            @Override
            public void onComplete() {
                System.out.println("=================onComplete");

            }
        };
        observable.subscribe(observer);
    }
}
