package com.desgin.structure_type._01_proxy.forced_proxy;

/**
 * author: King.Z <br>
 * date:  2017/10/1 17:30 <br>
 * description: 强制代理模式
 * 需要从真实对象访问代理角色，不允许直接访问真实角色 <br>
 */
public class TestClient {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("100012","大司马","艾欧尼亚");
        //获得指定的代理
        IGamePlayer proxy = player.getProxy();
        System.out.println("开始时间是：2017-6-24 10:45");
        proxy.login("100012", "123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是：2017-6-25 03:40");
    }
}
