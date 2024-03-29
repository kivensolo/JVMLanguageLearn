package com.nio.tcp_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NonBlockingClient
 */
public class SimpleClicentOneshot {
    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress(SimpleNioEchoServer.HOST, SimpleNioEchoServer.PORT));
        client.configureBlocking(false);

        String message = "Hello, Server!";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        client.write(buffer);

        // Read echo from the server
        buffer.clear();
        while (client.read(buffer) > 0) {
            buffer.flip();
            byte[] receivedData = new byte[buffer.remaining()];
            buffer.get(receivedData);
            System.out.println("Received echo: " + new String(receivedData).trim());
            buffer.clear();
        }

        client.close();
    }
}
