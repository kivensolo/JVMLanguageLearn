package com.nio.tcp_server;

import java.io.IOException;

/**
 * 开启Server后，可使用telnet连接,语法为:
 * telnet ip prot
 * 如:
 * telnet localhost 58889   //"0.0.0.0:58889"
 * telnet 127.0.0.1 58889   //"127.0.0.1:58889"
 *
 * netstat -ano // 查看所有端口及占用情况
 */
public class NIOServerTest {

    public static void main(String[] args) {
        SimpleTcpServer nioServer = new SimpleTcpServer();
        try {
            nioServer.start();
            nioServer.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
