package com.proxy.dynamic_proxy;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/6/24 17:27 <br>
 * description: 游戏者对象 <br>
 *
 *  动态代理，不需要创建代理类
 */
public class GamePlayer implements IGamePlayer {
    String name = "";

    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String psd) {}

    @Override
    public void killBoss() {
        System.out.println(name + "的游戏人物在杀Boss");
    }

    @Override
    public void upgrade() {
        System.out.println(name + "的游戏人物在杀升级了");
    }
}
