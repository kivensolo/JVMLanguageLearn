package com.io.nio.c_s_mode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * NIO方式实现的Server
 */
public class NIOServer {

    // 1. serverSelector负责轮询是否有新的连接，服务端监测到新的连接之后，不再创建一个新的线程，
    // 而是直接将新连接绑定到clientSelector上，这样就不用 IO 模型中 1w 个 while 循环在死等
    private Selector serverSelector;
    // 2. clientSelector负责轮询处理已有连接是否有数据可读
    private Selector clientSelector;

    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化的操作
     * 对应IO编程中服务端启动
     *
     * @param port 绑定的端口号
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        initServer(null, port);
    }

    public void initServer(String addr, int port) throws IOException {
        InetSocketAddress localAddr;
        if (addr == null || addr.equals("")) {
            localAddr = new InetSocketAddress(port);
        } else {
            localAddr = new InetSocketAddress(addr, port);
        }
        ServerSocketChannel listenerChannel = ServerSocketChannel.open();   //  获得一个ServerSocket通道
        listenerChannel.socket().bind(localAddr);         //  将该通道对应的ServerSocket绑定到port端口
        listenerChannel.configureBlocking(false);                           //  设置通道为非阻塞
        serverSelector = Selector.open();                                   //  获得一个通道管理器
        listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);   //  将通道管理器和该通道绑定

        clientSelector = Selector.open();

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
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        SelectionKey selectionKey;
                        while (keyIterator.hasNext()) {
                            selectionKey = keyIterator.next();
                            hander(selectionKey);
                            // 删除已经选择的key，防止重复处理
                            keyIterator.remove();
                        }
                    }
                }
            } catch (IOException ignored) {
            }
        });
        thread.setName("NIO-Server");
        thread.start();

        // 单独开线程处理read的方式
        //new Thread(() -> {
        //    try {
        //        while (true) {
        //            // (2) 批量轮询是否有哪些连接有数据可读
        //            if (clientSelector.select(1) > 0) {
        //                Set<SelectionKey> set = clientSelector.selectedKeys();
        //                Iterator<SelectionKey> keyIterator = set.iterator();
        //
        //                while (keyIterator.hasNext()) {
        //                    SelectionKey key = keyIterator.next();
        //                    handlerRead(keyIterator, key);
        //                }
        //            }
        //        }
        //    } catch (IOException ignored) {
        //    }
        //}).start();
    }

    private void hander(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            handlerAccept(key);
        } else if (key.isReadable()) {
            handlerRead(key);
        }
    }

    private void handlerRead(SelectionKey key) throws IOException {
        if (key.isReadable()) {
            try {
                SocketChannel clientChannel = (SocketChannel) key.channel();
                // 创建读取的缓冲区
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                // (3) 面向 Buffer
                int read = clientChannel.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    //System.out.print("服务端收到信息：");
                    //System.out.println(Charset.defaultCharset()
                    //        .newDecoder()
                    //        .decode(byteBuffer)
                    //        .toString());

                    byte[] data = byteBuffer.array();
                    String msg = new String(data).trim();
                    System.out.println("服务端收到信息：" + msg);

                    //回写数据给客户端
                    ByteBuffer outBuffer = ByteBuffer.wrap("I'm Server, I recevied you message.\n".getBytes());
                    clientChannel.write(outBuffer);
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
        // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
        // 获得和客户端连接的通道
        SocketChannel clientChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        clientChannel.configureBlocking(false);
        // 在这里可以给客户端发送信息
        System.out.println("新的客户端连接");

        // 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限
        clientChannel.register(serverSelector, SelectionKey.OP_READ);
    }
}


/*
 * 编程复杂、编程模型难
 * 还有以下让人诟病的问题：
 * JDK 的 NIO 底层由 epoll 实现，该实现饱受诟病的空轮询 bug 会导致 cpu 飙升 100%
 * 项目庞大之后，自行实现的 NIO 很容易出现各类 bug，维护成本较高
 */