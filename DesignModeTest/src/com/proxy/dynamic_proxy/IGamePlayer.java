package com.proxy.dynamic_proxy;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/6/24 17:26 <br>
 * description: 抽象的游戏行为 <br>
 */
public interface IGamePlayer {
    void login(String user, String psd);
    void killBoss();
    void upgrade();
}
