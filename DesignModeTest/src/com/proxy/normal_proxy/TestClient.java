package com.proxy.normal_proxy;

/**
 * Copyright(C) 2017, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/10/1 17:30 <br>
 * description: 普通代理模式用户不需要知道具体的代理者 <br>
 */
public class TestClient {
    public static void main(String[] args) {
        //定义一个痴迷的玩家
        IGamePlayer proxy = new GamePlayerProxy("张三");
        System.out.println("开始时间是：2017-6-24 10:45");
        proxy.login("zhangsan","123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是：2017-6-25 03:40");
    }
}
