package com.desgin.beahvior_type._01_observer;

/**
 * @description: 抽象观察角色
 */
public interface Observer {
    //更新接口(Pull Mode)
    void updata(Subject subject);
}
