package com.proxy.forced_proxy;

/**
 * author: King.Z <br>
 * date:  2017/6/24 17:26 <br>
 * description: 强制代理   需要明确自定的代理者才能执行行为 <br>
 */
public interface IGamePlayer {
    void login(String user, String psd);
    void killBoss();
    void upgrade();
    //强制代理  真实角色可以指定一个自己的代理对象
    IGamePlayer getProxy();
}
