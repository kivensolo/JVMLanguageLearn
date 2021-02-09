package com.io.nio.c_s_mode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClientTest {
    public static void main(String[] args) throws IOException {
        // 1.创建选择器
        Selector selector = Selector.open();
        // 2.创建一个NIO的SocketChannel，供网络数据通信
        SocketChannel socketChannel = SocketChannel.open();
        // 3.设置为非阻塞
        socketChannel.configureBlocking(false);
        // 4.连接确认好的ip及端口
        socketChannel.connect(new InetSocketAddress(NIOServerTest.HOST, NIOServerTest.PORT));

        // 5.把channel注册到选择器上  可操作选项为Connect
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        new Thread(() -> {
            while (true){
                try {
                    // 6. 选择器开始选择
                    selector.select();

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    if (selectionKeys.size() > 0){
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()){
                            SelectionKey key = iterator.next();
                            SocketChannel mChannel = (SocketChannel) key.channel();
                            if(key.isConnectable()){
                                // 可连接后，注册写事件到选择器
                                socketChannel.register(selector,SelectionKey.OP_WRITE);
                            }else if(key.isWritable()){
                                byteBuffer.flip();
                                byteBuffer.put("hi".getBytes());
                                socketChannel.write(byteBuffer);
                                byteBuffer.clear();
                                // 注册一个读操作
                                mChannel.register(selector, SelectionKey.OP_READ);
                            }else if(key.isReadable()){
                                byteBuffer.flip();

                                while (mChannel.read(byteBuffer) != -1) {
                                    //channel.read(buf);
                                    System.out.println("客户端读取数据");
                                }
                                ///取消write操作的注册，防止多次write
                                key.cancel();
                            }
                            iterator.remove();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
