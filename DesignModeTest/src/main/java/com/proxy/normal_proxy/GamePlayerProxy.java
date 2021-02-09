package com.proxy.normal_proxy;

/**
 * author: King.Z <br>
 * date:  2017/10/1 17:23 <br>
 * description: 具体代理商,实现真实游戏玩家的功能代理。<br>
 *    实现代理对象的抽象行为接口
 */
public class GamePlayerProxy implements IGamePlayer {

    private ReallyGamePlayer player;

    public GamePlayerProxy(String name) {
        //可以创建具体的游戏者对象  此处我用Base类代替
        player = new ReallyGamePlayer(name);
    }

    @Override
    public void login(String user, String psd) {
        doBeforThing();
        player.login(user, psd);
        doAfterThing();
    }

    @Override
    public void killBoss() {
        this.player.killBoss();
    }

    @Override
    public void upgrade() {
        this.player.upgrade();
    }


    private void doBeforThing() {
        //执行具体对象前代理者可以处理的事情
    }


    private void doAfterThing() {
        //执行具体对象后代理者可以处理的事情
    }
}
