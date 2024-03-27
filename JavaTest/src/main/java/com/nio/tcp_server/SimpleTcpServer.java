package com.nio.tcp_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


/**
 * 题目描述：
 * 设计并实现一个基于Java NIO的非阻塞TCP服务器，能够处理多个客户端并发连接请求。服务器应具备以下功能：
 * <p>
 * 1、使用 Selector 选择器监听并管理客户端连接请求。
 * 2、使用 ServerSocketChannel 接收新连接，并为每个新连接分配一个独立的SocketChannel。
 * 3、客户端发送字符串消息到服务器时，服务器应能正确接收并回显相同的消息给客户端。
 * 4、服务器应能够通过复用已注册的Selector在单个线程内高效地处理多个客户端通信。
 */
public class SimpleTcpServer {

    public static final String HOST = "localhost";
    public static final int PORT = 58889;


    // 1. serverSelector负责轮询是否有新的连接，服务端监测到新的连接之后，不再创建一个新的线程，
    // 而是直接将新连接绑定到clientSelector上，这样就不用 IO 模型中 1w 个 while 循环在死等
    private Selector serverSelector;

    public void start() throws IOException {
        start(HOST, PORT);
    }

    /**
     * 启动服务端监听
     */
    public void start(int port) throws IOException {
        start(null, port);
    }

    public void start(String addr, int port) throws IOException {
        InetSocketAddress localAddr;
        if (addr == null || addr.equals("")) {
            localAddr = new InetSocketAddress(port);
        } else {
            localAddr = new InetSocketAddress(addr, port);
        }
        serverSelector = Selector.open();
        ServerSocketChannel _channel = ServerSocketChannel.open();
        _channel.configureBlocking(false);
        _channel.socket().bind(localAddr);
        _channel.register(serverSelector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，
     * 如果有的话，则进行处理
     */
    public void listen() {
        System.out.println("服务端启动成功！开始监听客户端连接及数据请求");
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    // 当该事件到达后,selector.select()会返回, 否则，该方法会一直阻塞
                    if (serverSelector.select(1) > 0) {
                        // 获得selector中选中的项的迭代器，选中的项为注册的事件
                        Iterator<SelectionKey> keyIterator = serverSelector.selectedKeys().iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            // 删除已经选择的key，防止重复处理
                            keyIterator.remove();
                            handleKey(key);
                        }
                    }
                }
            } catch (IOException ignored) {
            }
        });
        thread.setName("NIO_Simple_TCP_Server");
        thread.start();
    }

    /**
     * 处理操作兴趣集
     * @param key SelectionKey
     */
    private void handleKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            handlerAccept(key);
        } else if (key.isReadable()) {
            handlerRead(key);
        }
    }

    private void handlerRead(SelectionKey key) throws IOException {
        if (key.isReadable()) {
            try (SocketChannel clientChannel = (SocketChannel) key.channel()) {
                // 创建读取的缓冲区
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                // (3) 面向 Buffer
                int numRead  = clientChannel.read(byteBuffer);
                if (numRead  > 0) {
                    byteBuffer.flip();

                    byte[] data = byteBuffer.array();
                    String msg = new String(data).trim();
                    //System.out.println(Charset.defaultCharset()
                    //        .newDecoder()
                    //        .decode(byteBuffer)
                    //        .toString());
                    System.out.println("服务端收到信息：" + msg);

                    //回写数据给客户端
                    ByteBuffer outBuffer = ByteBuffer.wrap(("Hi, I recevied you message:\n" + msg).getBytes());
                    clientChannel.write(outBuffer);
                    outBuffer.clear();
                    byteBuffer.clear();
                } else {
                    System.out.println("客户端关闭");
                    key.cancel();
                }
            } finally {
                // FIXME 客户端kill的时候,会出现
                //  Exception in thread "NIO-Server" java.nio.channels.CancelledKeyException
                key.interestOps(SelectionKey.OP_READ);
            }
        }
    }

    /**
     * 处理客户端请求连接事件
     *
     * @param selectionKey
     * @throws IOException
     */
    private void handlerAccept(SelectionKey selectionKey) throws IOException {
        // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到serverSelector
        // 获得和客户端连接的通道
        try (SocketChannel clientChannel = ((ServerSocketChannel) selectionKey.channel()).accept()) {
            clientChannel.configureBlocking(false);
            // 在这里可以给客户端发送信息
            System.out.println("新的客户端连接+1");
            // 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限
            clientChannel.register(serverSelector, SelectionKey.OP_READ);
        }
    }
}


/*
 * 编程复杂、编程模型难
 * 还有以下让人诟病的问题：
 * JDK 的 NIO 底层由 epoll 实现，该实现饱受诟病的空轮询 bug 会导致 cpu 飙升 100%
 * 项目庞大之后，自行实现的 NIO 很容易出现各类 bug，维护成本较高
 */