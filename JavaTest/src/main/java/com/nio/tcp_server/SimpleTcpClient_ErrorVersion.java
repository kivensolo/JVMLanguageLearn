package com.nio.tcp_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 最初编写的无效版本，服务端始终无法接收数据;
 */
@Deprecated
public class SimpleTcpClient_ErrorVersion {
    private static Selector selector;

    public static void main(String[] args) throws IOException {
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        clientChannel.connect(new InetSocketAddress(SimpleNioEchoServer.HOST, SimpleNioEchoServer.PORT));

        selector = Selector.open();
        clientChannel.register(selector, SelectionKey.OP_CONNECT);

        new Thread(() -> {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            System.out.println("启动客户端");
            //while (true) {
                try {
                    while (clientChannel.isOpen()) {
                        if(clientChannel.isConnectionPending()){
                            System.out.println("连接服务器中....");
                        }
                        if(clientChannel.isConnected()){
                            while (clientChannel.read(byteBuffer) > 0) {
                                byteBuffer.flip();
                                byte[] receivedData = new byte[byteBuffer.remaining()];
                                byteBuffer.get(receivedData);
                                System.out.println("Received echo: " + new String(receivedData).trim());
                                byteBuffer.clear();
                            }
                        }
                    }
//                    selector.select();
//
//                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
//                    if (!selectionKeys.isEmpty()) {
//                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
//                        while (iterator.hasNext()) {
//                            SelectionKey key = iterator.next();
//                            SocketChannel mChannel = (SocketChannel) key.channel();
//                            if (key.isValid() && key.isConnectable()) {
//                                if (!clientChannel.finishConnect()) {
//                                    System.out.println("Failed to finish connect");
//                                } else {
//                                    System.out.println("已成功连接服务端.");
////                                    clientChannel.register(selector, SelectionKey.OP_WRITE);
//                                }
//                            } else if (key.isValid() && key.isWritable()) {
////                                if(clientChannel.isConnected()){
////                                    System.out.println("已成功连接服务端，发送数据...");
////                                }
////                                SocketChannel channel = (SocketChannel) key.channel();
////                                handleWrite(byteBuffer, channel);
//
//                                // 发送完成后，切换到读模式，等待服务器回传消息
////                                clientChannel.register(selector, SelectionKey.OP_READ);
////                                System.out.println("key isWritable.");
//                            } else if (key.isValid() && key.isReadable()) {
//                                handleRead(byteBuffer, key, mChannel);
//
//                                // 读取完成后，继续监听写事件，准备发送下一条消息
////                                key.interestOps(SelectionKey.OP_WRITE);
//                            }
//                            iterator.remove();
//                        }
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            //}
        }).start();

        AtomicInteger counts = new AtomicInteger();
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (clientChannel.isConnected()) {
                counts.getAndIncrement();
                //发五次就自动结束
                if(counts.get() == 20){
                    try {
                        clientChannel.close();
                        System.out.println("客户端断开.");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    executor.shutdown();
                    return;
                }
                try {
                    String msg = "Hello,我是小波0_0" + counts;
                    System.out.println("客户端发送消息："+msg);
                    sendMessage(msg, clientChannel);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                } catch (IOException e) {
                    System.err.println("Error sending message: " + e.getMessage());
                    closeResources(clientChannel, selector);
                }
            } else {
                System.out.println("Not connected to server.");
            }
        }, 0, 5, TimeUnit.SECONDS);


    }

    private static void handleRead(ByteBuffer byteBuffer, SelectionKey key, SocketChannel mChannel) throws IOException {
        byteBuffer.flip();
        while (mChannel.read(byteBuffer) != -1) {
            String replay = StandardCharsets.UTF_8.decode(byteBuffer).toString();
            System.out.println(replay);
        }
        byteBuffer.clear();

        ///取消write操作的注册，防止多次write
        key.cancel();
    }

    private static void sendMessage(String msg, SocketChannel channel) throws IOException {
        System.out.println("Write to channel...");

        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        //buffer.flip();
        //channel.write(buffer); //写数据到通道里(写给服务器)
        //buffer.clear();
    }

    private static void closeResources(SocketChannel channel, Selector selector) {
        try {
            if (channel != null && channel.isOpen()) {
                channel.close();
            }
            if (selector != null && selector.isOpen()) {
                selector.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
