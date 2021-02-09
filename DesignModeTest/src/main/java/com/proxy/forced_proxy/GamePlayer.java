package com.proxy.forced_proxy;

/**
 * author: King.Z <br>
 * date:  2017/6/24 17:27 <br>
 * description: 具体游戏对象A <br>
 */
public class GamePlayer implements IGamePlayer {

    private GamePlayerProxy myProxy = null;
    String qqId = "";  //id
    String name = "";  //昵称
    String server = "";  //服务器

    public GamePlayer(String qqId,String name,String server) {
        this.qqId = qqId;
        this.name = name;
        this.server = server;
    }

    @Override
    public void login(String user, String psd) {
        if(!isMyProxy()){
            System.out.println("请使用指定的代理类");
        }else{
            System.out.println("账号:" + qqId + "  登录成功");
        }
    }

    @Override
    public void killBoss() {
         if(!isMyProxy()){
            System.out.println("请使用指定的代理类");
        }else{
             System.out.println("账号:" + qqId + "  正在玩游戏中");
        }
    }

    @Override
    public void upgrade() {
        if(!isMyProxy()){
            System.out.println("请使用指定的代理类");
        }else{
            System.out.println("账号:" + qqId + "  游戏账号升级");
        }
    }

    @Override
    public IGamePlayer getProxy() {
        if(myProxy == null){
            //找到自己的代理类
            myProxy = new GamePlayerProxy(this);
        }
        return myProxy;
    }

    private boolean isMyProxy(){
        return myProxy != null;
    }
}
