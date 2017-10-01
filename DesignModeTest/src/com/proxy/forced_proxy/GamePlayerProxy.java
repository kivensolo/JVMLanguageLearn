package com.proxy.forced_proxy;


/**
 * author: King.Z <br>
 * date:  2017/10/1 17:23 <br>
 * description: 代理类可以为真是角色预处理消息，过滤消息，消息转发，事后处理消息等功能
 * 每个代理类还可以代理多个真实角色(接多个单子)<br>
 */
public class GamePlayerProxy implements IGamePlayer, IPriceCount {
    private String proxyName = "9527";
    IGamePlayer _gamePlayer;

    public GamePlayerProxy(IGamePlayer _gamePlayer) {
        this._gamePlayer = _gamePlayer;
    }

    @Override
    public void login(String user, String psd) {
        doBeforThing();
        this._gamePlayer.login(user, psd);
    }

    @Override
    public void killBoss() {
        this._gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this._gamePlayer.upgrade();
        System.out.println("升级完成");
        count();
    }

    @Override
    public IGamePlayer getProxy() {
        //代理的代理暂时没有  就是他自己
        return this;
    }

    private void doBeforThing() {
        System.out.println("编号：" + proxyName +" 代练登记单子");
    }

    private void doAfterThing() {
        //执行具体对象后代理者可以处理的事情
    }

    @Override
    public void count() {
        System.out.println("编号：" + proxyName + "  收费100元");
    }
}
