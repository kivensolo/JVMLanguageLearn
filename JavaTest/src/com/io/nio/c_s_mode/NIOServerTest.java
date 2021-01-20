package com.io.nio.c_s_mode;

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
        NIOServer nioServer = new NIOServer();
        try {
            nioServer.initServer("127.0.0.1", 58889);
            nioServer.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
