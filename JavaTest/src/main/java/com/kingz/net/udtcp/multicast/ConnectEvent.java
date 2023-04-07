package com.kingz.net.udtcp.multicast;

public interface ConnectEvent {
    void onConnect(String ip, int prot, String xx);

    void onReceive(String ip, int prot, String xx, String paramString3);

    void onDisconnect(String ip, int prot, String xx);
}
