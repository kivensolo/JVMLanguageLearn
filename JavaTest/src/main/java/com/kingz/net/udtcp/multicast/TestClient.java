package com.kingz.net.udtcp.multicast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestClient {
    public static void main(String[] args) {
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ConnectEvent event = new ConnectEvent() {

            @Override
            public void onConnect(String ip, int prot, String xx) {
                System.out.println("onConnect");
            }

            @Override
            public void onReceive(String ip, int prot, String xx, String paramString3) {
                System.out.println("onReceive");

            }

            @Override
            public void onDisconnect(String ip, int prot, String xx) {
                System.out.println("onDisconnect");
            }
        };

        //TCP init
        PCRemoteServer remoteServer = new PCRemoteServer.TcpBuilder()
                .ip(hostAddress)
                .port(8991)
                .accept(event)
                .create();
        if (remoteServer != null) {
            //等待TCP链接
            remoteServer.acceptSync(new RemoteTcpConnection(event));

            //Udp init
            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    //if(i % 3 == 0){
                    //    pcRemoteServer.find("172.31.9.230","com.starcor.hunan");
                    //}else{
                        remoteServer.find();
                    //}
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }
}
