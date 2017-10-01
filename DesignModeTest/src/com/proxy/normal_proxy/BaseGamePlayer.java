package com.proxy.normal_proxy;

/**
 * author: King.Z <br>
 * date:  2017/6/24 17:27 <br>
 * description: 具体游戏对象A <br>
 */
public class BaseGamePlayer implements IGamePlayer {
    String name = "";
    String pwd = "";

    public BaseGamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String psd) {
        //doBeforThing
        System.out.println(name + "的游戏账号登录成功");
        //doAfterThing
    }

    @Override
    public void killBoss() {
        System.out.println(name + "的游戏人物在杀Boss");
    }

    @Override
    public void upgrade() {
        System.out.println(name + "的游戏人物在杀升级了");
        System.out.println(name + "的游戏账号退出");
    }
}
