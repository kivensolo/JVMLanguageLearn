package com.kingz.net.udtcp.multicast;


import java.net.Socket;

public interface ITCPConnector
{
    void onClose();

    void onConnect(Socket socket);
}
