package com.desgin.structure_type._01_proxy.forced_proxy;

/**
 * author: King.Z <br>
 * date:  2017/6/24 17:26 <br>
 * description: 代理行为抽象层
 *  <br>
 */
public interface IGamePlayer {
    void login(String user, String psd);
    void killBoss();
    void upgrade();
    //强制代理  真实角色可以指定一个自己的代理对象
    IGamePlayer getProxy();
}
