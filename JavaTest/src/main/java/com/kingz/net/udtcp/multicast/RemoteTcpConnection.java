package com.kingz.net.udtcp.multicast;

import com.kingz.utils.CommonUtils;

import java.io.Closeable;
import java.io.DataInputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RemoteTcpConnection implements ITCPConnector {
    private static final String HEARTBEAT_PREFIX = "@@";
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final HashSet<APMClient> clients = new HashSet();
    private final ConnectEvent eventCallback;

    public RemoteTcpConnection(ConnectEvent eventCallback) {
        this.eventCallback = eventCallback;
    }

    @Override
    public void onClose() {

    }

    @Override
    public void onConnect(Socket socket) {
        System.out.println("RemoteTcpConnection onConnect.... Create apmClient");
        String ip = socket.getInetAddress().getHostAddress();
        int port = socket.getPort();

        //创建客户端对象
        APMClient apm = new APMClient(ip, port, socket);
        synchronized (clients) {
            if (clients.add(apm)) {
                this.executor.submit(apm);
            } else {
                apm.close();
                System.err.println("[APMReceiver]: add connection error! destroy socket. " + apm.getFlag());
            }
        }
    }

    /**
     * 接收TCP数据的任务
     */
    private final class APMClient implements Runnable, Closeable {
        final String ip;
        final int port;
        private String rcId;
        private Socket conn;
        private long refreshTime;

        APMClient(String ip, int port, Socket conn) {
            this.ip = ip;
            this.port = port;
            this.conn = conn;
        }

        public void run() {
            DataInputStream inStream = null;
            try {
                inStream = new DataInputStream(this.conn.getInputStream());
                String line;
                //FIXME readLine阻塞
                while ((!conn.isClosed()) && (null != (line = inStream.readLine()))) {
                    String content = URLDecoder.decode(line);
                    boolean isHeartbeat;
                    if (content.startsWith(HEARTBEAT_PREFIX)) {
                        isHeartbeat = true;
                        if (CommonUtils.isEmpty(this.rcId)) {
                            this.rcId = content.substring(HEARTBEAT_PREFIX.length());
                            System.out.println(">> connect: " + getFlag());
                            synchronized (eventCallback) {
                                eventCallback.onConnect(this.ip, this.port, this.rcId);
                            }
                        }
                    } else {
                        isHeartbeat = false;
                    }
                    this.refreshTime = System.currentTimeMillis();
                    if (!isHeartbeat) ;
                    synchronized (eventCallback) {
                        eventCallback.onReceive(this.ip, this.port, this.rcId, line);
                    }
                }
            } catch (Exception e) {
                System.err.println("[APMReceiver]: " + getFlag() + ": " + e.getMessage());
            } finally {
                System.out.println(">> disconnect: " + getFlag());
                CommonUtils.closeSafety(inStream);
                close();

                clients.remove(this);
                synchronized (eventCallback) {
                    eventCallback.onDisconnect(this.ip, this.port, this.rcId);
                }
            }
        }

        public void close() {
            CommonUtils.closeSafety(this.conn);
            this.conn = null;
        }

        private String getFlag() {
            return this.ip + ":" + this.port + "@" + this.rcId;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if ((o == null) || (super.getClass() != o.getClass())) return false;
            APMClient apmClient = (APMClient) o;
            return (this.port == apmClient.port) && (Objects.equals(this.ip, apmClient.ip));
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.ip, Integer.valueOf(this.port)});
        }
    }
}
