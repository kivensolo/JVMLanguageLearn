package com.kingz.net.udtcp.multicast;

import com.kingz.utils.CommonUtils;

import java.io.Closeable;
import java.io.IOException;
import java.net.*;

/**
 * 支持TCP连接的服务端
 * 用于客户端从udp转tcp连接通信
 */
public final class PCRemoteServer implements Closeable {
    //Tcp服务端Socket对象
    private final ServerSocket tcpSs;
    private ITCPConnector tcpConnector;
    final String tcpIp;
    final int tcpPort;

    private static final String IPV4_LOCAL_BROAD_CAST = "255.255.255.255";
    public static final String IPV4_MULTI_CAST_GROUP = "239.255.255.252"; //224.0.0.225也可以  224.0.0.1 不行
    public static final int IPV4_MULTI_CAST_PROT = 34888;

    private PCRemoteServer(ServerSocket ss) {
        this.tcpSs = ss;
        this.tcpIp = ss.getInetAddress().getHostAddress();
        this.tcpPort = ss.getLocalPort();
        System.out.println(">> RemoteServer open serversocket in: " + this.tcpIp + ":" + this.tcpPort);
    }

    /**
     * 创建TCP服务端
     * @param ip    the specified host witch will bind to
     * @param port  the port number
     * @return Server对象
     */
    public static PCRemoteServer create(String ip, int port) {
        try {
            ServerSocket ss;
            if (null == ip) {
                ss = new ServerSocket(port, 100);
            } else {
                ss = new ServerSocket(port, 100, InetAddress.getByName(ip));
            }
            return new PCRemoteServer(ss);
        } catch (IOException e) {
            System.err.println("[RemoteServer]: " + ip + ":" + port + " wait error! " + e.getMessage());
            return null;
        }
    }

    public void acceptSync(ITCPConnector connector){
        if(connector == null){
            throw new NullPointerException("TCPConnector can't null");
        }
        if(tcpSs.isClosed()){
            return;
        }
        new Thread(() -> {
            Exception exitCause;
           while (true){
               if(!tcpSs.isClosed()){
                   try {
                       Socket acceptSocket = tcpSs.accept();
                       connector.onConnect(acceptSocket);
                   } catch (IOException e) {
                       exitCause = e;
                       System.out.println(
                               ">> " + tcpIp + ":" + tcpPort + " connector closed" +
                                       ", cause: " + exitCause.getMessage());
                       connector.onClose();
                   }
               }
           }
        }).start();
    }



    public void find() {
        find(null, "*");
    }

    /**
     * 查找远程监听了udp端口的客户端
     * @param deviceIp    设备ip
     * @param rcId      接受客户端的Id，一般是包名, *表示不筛选
     */
    public void find(String deviceIp, String rcId) {
        find(this.tcpIp, this.tcpPort, deviceIp, rcId, null);
    }

    /**
     * 发送UDP协议数据报包，查找客户端
     * @param tcpIp     客户端可通过tcp连接的ip地址
     * @param tcpPort   客户端可通过tcp连接的端口地址
     * @param device    设备名称(包名？？？？)
     * @param rcId      rcId是什么？
     * @return 数据是否发送成功
     */
    private static boolean find(String tcpIp, int tcpPort, String device,
                                String rcId,NetworkInterface iface) {
        boolean sendSucess = false;
        DatagramSocket socket = null;
        InetAddress address = null;
        String type="";
        try {
            if (!CommonUtils.isEmpty(device)) {// 明确目标设备，则走单播
                type= "单播";
                socket = new DatagramSocket();
                address = InetAddress.getByName(device);
            //} else if (CommonUtils.isWifi()) {
            } else {
                type= "组播";
                address = InetAddress.getByName(IPV4_MULTI_CAST_GROUP);
                InetSocketAddress inetSocketAddress = new InetSocketAddress(address, IPV4_MULTI_CAST_PROT);
                MulticastSocket ms = new MulticastSocket(IPV4_MULTI_CAST_PROT);
                ms.setNetworkInterface(iface);
                ms.joinGroup(inetSocketAddress, iface);
                //ms.setLoopbackMode(true);
                socket = ms;
            }
            //else {
            //    socket = new DatagramSocket();
            //    address = InetAddress.getByName(IPV4_LOCAL_BROAD_CAST);
            //}
            String message = String.format("%s,%s,%s", tcpIp, tcpPort, rcId);

            DataPackage pkg = new DataPackage();
            byte[] buf = DataPackage.createBuf();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 0);
            for (int port = 34887; port <= 34888; port++) {
                packet.setPort(port); //update package
                pkg.version = DataPackage.VERSION;
                pkg.len = (short)message.length();
                pkg.data = message.getBytes();
                pkg.encodeToBytes(buf);
                socket.send(packet);
            }
            sendSucess = true;
            System.out.println("[RemoteServer]: send request success!  Content=" + message);
        } catch (Exception e) {
            CommonUtils.closeSafety(socket);
            System.err.println(String.format("[RemoteServer]: send request error! %s [%s]",e.getMessage(), type));
        } finally {
            if (socket instanceof MulticastSocket && null != address) {
                try {// 关闭socket并退出组播地址
                    MulticastSocket multicastSocket = (MulticastSocket) socket;
                    multicastSocket.leaveGroup(address);
                    multicastSocket.close();
                } catch (IOException ignored) {}
            }
        }
        return sendSucess;
    }

    public void findSync(){
        findSync(null, "*", null);
    }

    /**
     * 异步查找组播数据接收者
     * @param iface
     * @param device
     * @param rcId
     */
    public void findSync(String device, String rcId, NetworkInterface iface) {
        new Thread(() -> {
            while (!PCRemoteServer.this.tcpSs.isClosed()) {
                PCRemoteServer.find(tcpIp, tcpPort, device, rcId, iface);
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException ignored) {
                }
            }
        }).start();
    }


    @Override
    public void close() throws IOException {
        CommonUtils.closeSafety(tcpSs);
    }


    public static final class TcpBuilder {
        private String ip;
        private int port;
        private String device;
        private String rcId;
        private NetworkInterface sendInterface;
        private ITCPConnector tcpConnector;
        private Runnable closeAction;

        public TcpBuilder() {
            this.ip = null;
            this.port = 0;
            this.device = null;
            this.rcId = "*";
            this.tcpConnector = null;
            this.closeAction = null;
        }
        public TcpBuilder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public TcpBuilder port(int port) {
            this.port = port;
            return this;
        }

        public TcpBuilder device(String device) {
            this.device = device;
            return this;
        }

        public TcpBuilder Id(String rcId) {
            this.rcId = rcId;
            return this;
        }

        public TcpBuilder networkInterface(NetworkInterface iface) {
            this.sendInterface = iface;
            return this;
        }

        public TcpBuilder accept(ConnectEvent event) {
            this.tcpConnector = new RemoteTcpConnection(event);
            return this;
        }

        public TcpBuilder onClose(Runnable closeAction) {
            this.closeAction = closeAction;
            return this;
        }

        public PCRemoteServer create() {
            PCRemoteServer remoteServer = PCRemoteServer.create(this.ip, this.port);
            if (null == remoteServer) {
                return null;
            }
            if (null != this.closeAction){
                remoteServer.acceptSync(new ITCPConnector()
                {
                    public void onClose() {
                        closeAction.run();
                    }

                    public void onConnect(Socket socket){
                        tcpConnector.onConnect(socket);
                    }
                });
            }
            else {
                remoteServer.acceptSync(tcpConnector);
            }

            remoteServer.findSync(this.device, this.rcId, sendInterface);
            return remoteServer;
        }
    }
}
