package com.kingz.net.udtcp.multicast;

import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;

public class TestClient {
    public static void main(String[] args) {
        String hostAddress = null;
        NetworkInterface iface = null;
        try {
            // If enable Hyper-V, This hostAddress will be "vEthernet (Default Switch)",
            // not real physical network adapter
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            boolean findPhysicalNetIp = false;
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements() && !findPhysicalNetIp){
                NetworkInterface ni = networkInterfaces.nextElement();
                if (!ni.isVirtual() && !ni.isLoopback() && ni.isUp() && !ni.isLoopback()) { // 过滤掉虚拟接口和回环接口
                    System.out.println("Interface name: " + ni.getName());
                    System.out.println("    Display name: " + ni.getDisplayName());
                    System.out.println("    MAC address : " + Arrays.toString(ni.getHardwareAddress()));
                    System.out.print("    IP addresses: ");
                    Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress addr = inetAddresses.nextElement();
                        if (addr instanceof Inet4Address) {
                            String ip = addr.getHostAddress();
                            System.out.print(ip + "\n");
                            if(ip.contains("172.31.")){
                                hostAddress = ip;
                                findPhysicalNetIp = true;
                                iface = ni;
                                break;
                            }
                        }

                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.print("physical network adapter    IP addresses: " + hostAddress);


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
                .Id("hello.kingz")
                .port(8991)
                .accept(event)
                .networkInterface(iface)
                .create();
        //if (remoteServer != null) {
        //    //等待TCP链接
        //    remoteServer.acceptSync(new RemoteTcpConnection(event));
        //
        //    //Udp init
        //    new Thread(() -> {
        //        for (int i = 0; i < 1; i++) {
        //            //if(i % 3 == 0){
        //            //    pcRemoteServer.find("172.31.9.230","com.starcor.hunan");
        //            //}else{
        //                remoteServer.find("172.31.9.230","com.kingz.hello");
        //            //}
        //            try {
        //                Thread.sleep(2000);
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //        }
        //    }).start();
        //}
    }
}
