package com.desgin.structure_type._01_proxy.dynamic_proxy;

/**
 * author: King.Z <br>
 * date:  2017/6/24 17:26 <br>
 * description: 抽象的游戏行为 <br>
 */
public interface IGamePlayer {
    void login(String user, String psd);
    void killBoss();
    void upgrade();
}
