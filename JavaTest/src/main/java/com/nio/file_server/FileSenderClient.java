package com.nio.file_server;

import com.nio.tcp_server.SimpleNioEchoServer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class FileSenderClient {


    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress(NIOFTPServer.HOST, NIOFTPServer.PORT));
        client.configureBlocking(false);

        //读取本地文件数据传给服务端
        RandomAccessFile raf = new RandomAccessFile(".\\JavaTest\\java_pid3356.hprof", "r");
        FileChannel channel = raf.getChannel();
        long fileSizeBytes = channel.size();
        ByteBuffer byteBuffer = ByteBuffer.allocate(8 * 1024);
        long writeSizeBytes = 0;
        long time = System.currentTimeMillis();
        while (channel.read(byteBuffer) > 0) {
            writeSizeBytes += byteBuffer.position();
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                client.write(byteBuffer);
            }
            byteBuffer.clear();

            updateProgress(writeSizeBytes, fileSizeBytes);
        }
        long costTime = (System.currentTimeMillis() - time) / 1000;
        System.out.println("\n总耗时：" + costTime +"s");
        client.close();
    }

    private static final String PROGRESS_BAR = "|---|";
    private static final String EMPTY_BAR = " ";
    private static final String COMPLETE_BAR = "█";

    private static void updateProgress(long progress, long max) {
        double percent = (double) progress / max * 100;
        // 计算已完成的进度条部分和未完成的进度条部分
        int completedBars = (int) (percent / 5);
        int emptyBars = 20 - completedBars;

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < completedBars; j++) {
            sb.append(COMPLETE_BAR);
        }
        for (int j = 0; j < emptyBars; j++) {
            sb.append(EMPTY_BAR);
        }
        sb.append(PROGRESS_BAR);
        sb.append(String.format("%.1f%%", percent));

        System.out.print("\r" + sb);
    }
}
