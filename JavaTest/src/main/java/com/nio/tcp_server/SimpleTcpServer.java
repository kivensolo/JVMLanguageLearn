package com.nio.tcp_server;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;


/**
 * 题目描述：
 * 设计并实现一个基于Java NIO的非阻塞TCP服务器，能够处理多个客户端并发连接请求。服务器应具备以下功能：
 * <p>
 * 1、使用 Selector 选择器监听并管理客户端连接请求。
 * 2、使用 ServerSocketChannel 接收新连接，并为每个新连接分配一个独立的SocketChannel。
 * 3、客户端发送字符串消息到服务器时，服务器应能正确接收并回显相同的消息给客户端。
 * 4、服务器应能够通过复用已注册的Selector在单个线程内高效地处理多个客户端通信。
 * <p>
 * <p>
 * 开启Server后，可使用telnet连接,语法为:
 * telnet ip prot
 * 如:
 * telnet localhost 58889   //"0.0.0.0:58889"
 * telnet 127.0.0.1 58889   //"127.0.0.1:58889"
 * <p>
 * netstat -ano // 查看所有端口及占用情况
 * netstat -ano | findstr ":59997" 过滤此服务端的端口信息
 * <p>
 * 连接后可以telnet发数据测试;若telnet命令无效:
 * “控制面板”--->“程序和功能”--->“启用或关闭Windows功能”--->勾选“Telnet客户端”
 * <p>
 */
public class SimpleTcpServer implements Closeable {

    public static void main(String[] args) {
        //try with resources
        SimpleTcpServer nioServer = new SimpleTcpServer();
        try {
            nioServer.start();
            nioServer.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String HOST = "localhost";
    public static final int PORT = 59997;


    /**
     * serverSelector负责轮询是否有新的连接，服务端监测到新的连接之后，不再创建一个新的线程，
     * 而是直接将新连接绑定到clientSelector上，这样就不用 IO 模型中 1w 个 while 循环在死等
     */
    private Selector serverSelector;

    private ServerSocketChannel serverSocketChannel;

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
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(localAddr);
        //[注意]:一定要bind后再打开Selector,否则客户端断开后,Server就不是 LISTENING 的状态了
        serverSelector = Selector.open();
        serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动成功！");
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，
     * 如果有的话，则进行处理
     */
    public void listen() {
        System.out.println("开始监听客户端连接及数据请求 port：" + PORT);
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    serverSelector.select(); // Wait for events

                    // 获得selector中选中的项的迭代器，选中的项为注册的事件
                    Iterator<SelectionKey> keyIterator = serverSelector.selectedKeys().iterator();

                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        keyIterator.remove(); // 删除已经选择的key，防止重复处理
                        if (!key.isValid()) {
                            System.out.println("key无效了");
                            continue;
                        }
                        handleKey(key);
                    }
                }
            } catch (IOException e) {
                System.out.println("循环异常：" + e);
            }
        });
        thread.setName("NIO_Simple_TCP_Server");
        thread.start();
    }

    /**
     * 处理操作兴趣集
     *
     * @param key SelectionKey
     */
    private void handleKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {// Accept new client connection
            acceptNewConnection(key);
        } else if (key.isReadable()) {// Handle read event
            handleRead(key);
        } else if (key.isWritable()) {
            handleWrite(key);
        }
    }

    /**
     * 处理客户端请求连接事件
     *
     * @param selectionKey
     * @throws IOException
     */
    private void acceptNewConnection(SelectionKey selectionKey) throws IOException {
        // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到serverSelector
        // 获得和客户端连接的通道
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel clientSocketChannel = serverSocketChannel.accept();
        clientSocketChannel.configureBlocking(false);

        SocketAddress remoteAddress = clientSocketChannel.getRemoteAddress();
        // Register OP_READ event
        clientSocketChannel.register(serverSelector, SelectionKey.OP_READ);
        System.out.println("Accepted new connection from " + remoteAddress);
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel sChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
   /*     if (!sChannel.isConnected()) {
            System.out.println("Channel is not connected，Close client socketChannel.");
            sChannel.close();
            return;
        }*/
        int numRead = sChannel.read(byteBuffer);
        if (numRead == -1) {
            //检测到客户端关闭连接，进行通道释放
            closeClientConnection(key);
        } else if (numRead > 0) {
            byteBuffer.flip(); //切换模式

            //byte[] data = byteBuffer.array();
            //String msg = new String(data).trim();
            String msg = Charset.defaultCharset().newDecoder().decode(byteBuffer).toString();
            System.out.println("[Recevied]：" + msg);

            //回写数据给客户端
            ByteBuffer writeBuffer = ByteBuffer.wrap(("Hi, I recevied you message:\t" + msg + "\r\n").getBytes());
            sChannel.write(writeBuffer);
            if (!writeBuffer.hasRemaining()) {
                key.interestOps(SelectionKey.OP_READ); // 如果缓冲区已清空，恢复到监听读事件
            }
            writeBuffer.clear();
            byteBuffer.clear();
        }
    }

    private void handleWrite(SelectionKey key) throws IOException {
        // 在本示例中，假定所有的写事件都是回显数据的结果
        // 实际项目中，写事件可能包含更多复杂的业务逻辑
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();
        // 假设在这里处理未完成的写操作
        // ...

        // 写操作完成后，恢复到监听读事件
        key.interestOps(SelectionKey.OP_READ);
    }

    /**
     * Client closed connection.
     *
     * @param key SelectionKey
     */
    private void closeClientConnection(SelectionKey key) {
        SocketAddress remoteAddress = null;
        try {
            SocketChannel clientSocketChannel = (SocketChannel) key.channel();
            remoteAddress = clientSocketChannel.getRemoteAddress();
            key.cancel();// 取消与该客户端关联的SelectionKey
            clientSocketChannel.close();
        } catch (ClosedChannelException e) {
            System.out.println("Client already disconnected:" + remoteAddress);
        } catch (Exception e) {
            System.err.println("Unexpected error closing client connection: " + e);
        }
    }


    @Override
    public void close() throws IOException {
        closeResources(serverSocketChannel, serverSelector);
    }

    private static void closeResources(ServerSocketChannel channel, Selector selector) throws IOException {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
        if (selector != null && selector.isOpen()) {
            selector.close();
        }
    }
}

/**
 * 编程复杂、编程模型难
 * 还有以下让人诟病的问题：
 * JDK 的 NIO 底层由 epoll 实现，该实现饱受诟病的空轮询 bug 会导致 cpu 飙升 100%
 * 项目庞大之后，自行实现的 NIO 很容易出现各类 bug，维护成本较高
 */