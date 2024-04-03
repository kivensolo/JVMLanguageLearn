package com.nio.file_server;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 题目描述：
 * 构建一个Java应用程序，它使用NIO的`FileChannel`与`Selector`结合实现一个简单的文件传输服务。
 * 服务器端接收客户端上传的文件，并将其保存到本地指定目录；
 * 客户端则可以从本地选择一个文件并将其发送到服务器。
 *
 * 1. 服务器端应当能够接收客户端发送的数据，将接收到的数据写入到本地文件系统的一个新文件中。
 * 2. 客户端应当允许用户选择一个本地文件，并通过`SocketChannel`以非阻塞方式将文件内容发送给服务器。
 * 3. 利用`ByteBuffer`进行数据读写，并确保在大量数据传输过程中，既能有效利用缓冲又能防止内存溢出。
 *
 * 要求：
 * - 设计并实现服务器和客户端两端的NIO数据传输逻辑。
 * - 解释如何利用`transferTo()`或`transferFrom()`方法优化大文件传输效率。
 * - 分析在高并发场景下，如果采用NIO进行文件传输相比传统IO有哪些改进。
 */
public class NIOFTPServer {

    public static void main(String[] args) throws IOException {
        new NIOFTPServer().start();
    }

    public static final String HOST = "localhost";
    public static final int PORT = 60001;

    FileChannel channel = null;
    ServerSocketChannel serverSocketChannel=null;
    Selector serverSelector =null;

    public NIOFTPServer() {
    }

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
//        RandomAccessFile raf = new RandomAccessFile("", "w");
//        channel = raf.getChannel();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(localAddr);

        serverSelector = Selector.open();
        serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

        startListen();
    }

    public void startListen() {
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
        thread.setName("NIO_Simple_FTP_Server");
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
        }
//        else if (key.isWritable()) {
//            handleWrite(key);
//        }
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

        RandomAccessFile raf = new RandomAccessFile("hprof_copy.hprof", "rw");
        FileChannel fileChannel = raf.getChannel();
        while (sChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        raf.close();
        closeClientConnection(key);
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
            System.out.println("Disconnect with client:" + remoteAddress);
        } catch (ClosedChannelException e) {
            System.out.println("Client already disconnected:" + remoteAddress);
        } catch (Exception e) {
            System.err.println("Unexpected error closing client connection: " + e);
        }
    }

}
