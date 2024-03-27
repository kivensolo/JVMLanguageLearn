package com.nio.tcp_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class SimpleTcpClient {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(SimpleTcpServer.HOST, SimpleTcpServer.PORT));
        socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        new Thread(() -> {
            System.out.println("启动");
            int counts = 0;
            while (true){
                try {
                    //阻塞等待直到至少有一个已注册到该Selector的通道变得可读、可写或已准备好进行连接等操作。
                    selector.select();

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    if (selectionKeys.size() > 0){
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()){
                            SelectionKey key = iterator.next();
                            SocketChannel mChannel = (SocketChannel) key.channel();
                            if(key.isConnectable()){
                                // 可连接后，注册读写事件到选择器
                                socketChannel.register(selector,SelectionKey.OP_WRITE| SelectionKey.OP_READ);

                                byteBuffer.put(("hi, 我是小波0_0 ").getBytes());
                                byteBuffer.flip();
                                socketChannel.write(byteBuffer);
                                byteBuffer.clear();
                            }else if(key.isWritable()){
                                System.out.println("Client send msg...");
                                byteBuffer.flip();
                                byteBuffer.put(("hi, 我是小波0_0---" + counts).getBytes());
                                socketChannel.write(byteBuffer);
                                byteBuffer.clear();
//                                mChannel.register(selector, SelectionKey.OP_READ);
                            }else if(key.isReadable()){
                                byteBuffer.flip();

                                while (mChannel.read(byteBuffer) != -1) {
                                    String replay = StandardCharsets.UTF_8.decode(byteBuffer).toString();
                                    System.out.println(replay);
                                }
                                byteBuffer.clear();

                                ///取消write操作的注册，防止多次write
                                key.cancel();
                            }
                            iterator.remove();
                        }
                        counts++;
                    }
                    Thread.sleep(1000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}
