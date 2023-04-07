package com.kingz.net.udtcp.multicast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 使用Java实现监听局域网组播UDP指定端口的示例代码
 */
public class MulticastListener {
    public static void main(String[] args) {
        simpleMulticastListener();
    }

    private static void simpleMulticastListener() {
        MulticastSocket socket = null;
        InetAddress group = null;
        System.out.println("监听器启动…");
        try {
            // 创建组播Socket并指定端口
            socket = new MulticastSocket(PCRemoteServer.IPV4_MULTI_CAST_PROT);
            // 指定组播地址
            group = InetAddress.getByName(PCRemoteServer.IPV4_MULTI_CAST_GROUP);
            // 将socket加入到组播地址中
            socket.joinGroup(group);

            byte[] buffer = new byte[523];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            //Thread receiverThread = new Thread(() -> {

            String contentData;
            while (true) {
                try {// 循环监听
                    socket.receive(packet); //阻塞方法，buffer填充满了，才会返回数据
                    contentData = ggetPacketContentData(packet.getData());
                    if(!contentData.equals("")){
                        System.out.println("接收到组播数据, 离开组播");
                        destoryMulticast(socket, group);
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("尝试进行TCP链接......");
            // TODO 切换为TCP长链接
            // 连接后，就发心跳，保持心跳为了逻辑上得知Server是否已断开
            String[] split = contentData.split(",");
            if(split.length > 2){
                String serverHost = split[0];
                String serverPort = split[1];
                // 创建客户端Socket对象，连接服务器
                Socket clientSocket = new Socket(serverHost, Integer.parseInt(serverPort));
                // 获取输入流和输出流
                OutputStream outToServer = clientSocket.getOutputStream();
                InputStream inFromServer = clientSocket.getInputStream();

                // 向服务器发送数据
                String message = "@@Hello, Server! I'm shakeHands with you.";
                byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
                outToServer.write(bytes);

                // 接收服务器返回的数据
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int length;
                while ((length = inFromServer.read(data)) != -1) {
                    baos.write(data, 0, length);
                }
                String response = baos.toString("UTF-8");

                // 输出服务器返回的数据
                System.out.println("Response from server: " + response);

                // 关闭连接
                //clientSocket.close();
            }

            //});
            //receiverThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            destoryMulticast(socket, group);
        }
    }

    private static void destoryMulticast(MulticastSocket socket, InetAddress group) {
        if (socket != null) {
            try {
                // 关闭socket并退出组播地址
                socket.leaveGroup(group);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String ggetPacketContentData(byte[] data){
        byte[] version = new byte[2];
        byte[] len = new byte[2];
        byte[] content = new byte[512];
        int offset = 7; //Header len
        System.arraycopy(data, offset, version, 0, version.length);
        offset += version.length;
        System.arraycopy(data, offset, len, 0, len.length);
        offset += len.length;
        short contentLen = ByteBuffer.wrap(len).getShort();
        System.arraycopy(data, offset, content, 0, contentLen);

        String contentStr = new String(content, StandardCharsets.UTF_8);
        String format = String.format("Ver=%d Len=%d Content=%s\n",
                ByteBuffer.wrap(version).getShort(), contentLen, contentStr);
        System.out.println("Received udp message: \n" + format);
        return contentStr;
    }

    private static void customMulticastListener() {
        MulticastSocket socket = null;
        InetAddress group = null;
        System.out.println("监听器启动…");
        try {
            // 创建组播Socket并指定端口
            socket = new MulticastSocket(9998);
            // 指定组播地址
            group = InetAddress.getByName("224.0.0.2");
            // 将socket加入到组播地址中
            socket.joinGroup(group);
            // 循环监听
            while (true) {
                byte[] buffer = new byte[32];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                //接收到数据，判断是否是要连接自己
                System.out.println("Received udp message: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            destoryMulticast(socket, group);
        }
    }
}
