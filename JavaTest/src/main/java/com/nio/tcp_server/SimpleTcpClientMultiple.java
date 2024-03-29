package com.nio.tcp_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NonBlockingClient with multiple send.
 */
public class SimpleTcpClientMultiple {

    private SocketChannel clientChannel;
    private final AtomicInteger counts = new AtomicInteger();
    ScheduledExecutorService fixedExecutor = Executors.newSingleThreadScheduledExecutor();

    public void start() throws IOException {
        clientChannel = SocketChannel.open();
        clientChannel.connect(new InetSocketAddress(SimpleNioEchoServer.HOST, SimpleNioEchoServer.PORT));
        clientChannel.configureBlocking(false);

        // 1. Schedule task to send messages every second
        fixedExecutor.scheduleAtFixedRate(this::sendMessage, 0, 1, TimeUnit.SECONDS);

        // 2. Start reading responses from the server in a separate thread
        new Thread(() -> {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                while (clientChannel.isOpen()) {
                    if(clientChannel.isConnected()){
                        readFromSocketChannel(buffer);
                    }else{
                        System.out.println("客户端还未连接");
                    }
                }
                System.out.println("Channel open failed!");
            } catch (IOException e) {
                System.err.println("Error reading from server: " + e.getMessage());
                closeResources();
            }
        }).start();
    }

    /**
     * 读取SocketChannel的数据
     * @param buffer
     * @throws IOException
     */
    private void readFromSocketChannel(ByteBuffer buffer) throws IOException {
        if(!clientChannel.isConnected()){
            return;
        }
        while (clientChannel.read(buffer) > 0) {
            buffer.flip(); //切换模式
            //分配buffer中现有数据大小的byte数组，用于接收数据
            byte[] receivedData = new byte[buffer.remaining()];
            buffer.get(receivedData);
            String content = new String(receivedData).trim();
            //String contentFromBuffer = StandardCharsets.UTF_8.decode(buffer).toString();

            System.out.println("[Server Response]:\n\t" + content);
            buffer.clear();
        }
    }

    private void sendMessage() {
        //if (clientChannel.isConnected()) {
        counts.getAndIncrement();
        //发10次就自动结束
        if (counts.get() == 5) {
            try {
                clientChannel.close();
                System.out.println("客户端主动断开.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fixedExecutor.shutdown();
            return;
        }

        String msg = "Hello,我是小波0_0 --- " + counts;
        System.out.println("[Send]:\n\t" + msg);

        ByteBuffer buffer = ByteBuffer.allocate(msg.getBytes().length);
        buffer.put(msg.getBytes());
        buffer.flip();
        //等同于
        //ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        try {
            while (buffer.hasRemaining()) {
                clientChannel.write(buffer);
            }
            buffer.clear();
        } catch (IOException e) {
            System.err.println("Error sending message: " + e.getMessage());
            closeResources();
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleTcpClientMultiple simpleTcpClientV2 = new SimpleTcpClientMultiple();
        simpleTcpClientV2.start();
    }

    private void closeResources() {
        try {
            if (clientChannel != null && clientChannel.isOpen()) {
                clientChannel.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
