package com.desgin.beahvior_type._01_observer;

/**
 * @description: 抽象主题角色： 添加、删除、通知更新
 */
public interface Subject {
    void attach(Observer observer); //增加一个观察者
    void detach(Observer observer); //删除一个观察者
    void notifyObservers();         //通知所有观察者刷新自己
}
