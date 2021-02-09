package com.observerMode.inter;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/5/28 17:32
 * description:主题接口
 * 注册、注销、通知观察者
 */
public interface Subject {
   public void registerObserver(ObserverZ obj);
   public void unregisterObserver(ObserverZ obj);
   public void notifyObservers();
}
