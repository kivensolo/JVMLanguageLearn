package com.desgin.beahvior_type._01_observer;

/**
 * 测试类
 */
public class TestClass {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer_A = new ConcreteObserver();
        Observer observer_B = new ConcreteObserver();
        subject.attach(observer_A);
        subject.attach(observer_B);
//        subject.notifyObservers();
        subject.changeState("boomxiakalaka");
    }
}
