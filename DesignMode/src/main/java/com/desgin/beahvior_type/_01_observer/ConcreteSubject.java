package com.desgin.beahvior_type._01_observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 具体主题角色
 */
public class ConcreteSubject implements Subject{

    //用一个容器来保存观察者对象
    private List<Observer> list;
    //默认状态值
    private String state = "default";

    public ConcreteSubject() {
        list = new ArrayList<>();
    }

    public String getState(){
        return state;
    }

    @Override
    public void attach(Observer observer) {
        if(observer == null){
            throw  new NullPointerException();
        }
        if(!list.contains(observer)){
            list.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        list.remove(observer);
    }

    /**
     * 通知位于容器中的所有观察者对象更新
     */
    @Override
    public void notifyObservers() {
        for (Observer observer:list){
            System.out.println("notifyObservers() +1");
            observer.updata(this);
        }
    }

    /**
     * 更改状态值
     * @param newState
     */
    public void changeState(String newState){
        state = newState;
        notifyObservers();
    }
}
