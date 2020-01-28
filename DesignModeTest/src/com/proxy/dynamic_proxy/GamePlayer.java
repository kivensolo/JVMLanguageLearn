package com.proxy.dynamic_proxy;

/**
 * author: King.Z <br>
 * date:  2017/10/1 17:26 <br>
 * description: 需要代理得具体对象 <br>
 */
public class GamePlayer implements IGamePlayer {
    private String name = "";

    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String psd) {
        System.out.println("游戏账号登录成功");
    }

    @Override
    public void killBoss() {
        System.out.println("游戏人物在杀Boss");
    }

    @Override
    public void upgrade() {
        System.out.println("游戏人物在杀升级了");
    }
}
