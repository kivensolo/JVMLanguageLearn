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
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(NIOFTPServer.HOST, NIOFTPServer.PORT));
        socketChannel.configureBlocking(false);

        //读取本地文件数据传给服务端
        RandomAccessFile raf = new RandomAccessFile(".\\JavaTest\\wumeizijiang.mp4", "r");
        FileChannel fileChannel = raf.getChannel();
        long fileSizeBytes = fileChannel.size();
        if(fileSizeBytes == 0){
            System.err.println("文件为空,请检查文件数据!");
            return;
        }

        long time = System.currentTimeMillis();
        /** 手动版本
        ByteBuffer byteBuffer = ByteBuffer.allocate(8 * 1024);
        long writeSizeBytes = 0;
        while (fileChannel.read(byteBuffer) > 0) {
            writeSizeBytes += byteBuffer.position();
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            byteBuffer.clear();

            updateProgress(writeSizeBytes, fileSizeBytes);
        }*/

        //高效版本
        long totalTransferredBytes = 0;
        while (totalTransferredBytes < fileSizeBytes) {
            //使用transferTo()方法将文件通道的数据传输到socketChannel
            long transferredBytes = fileChannel.transferTo(
                    totalTransferredBytes,
                    fileChannel.size() - totalTransferredBytes,
                    socketChannel
            );
            if (transferredBytes == -1) {
                throw new IOException("Error transferring data to the server.");
            }
            totalTransferredBytes += transferredBytes;
            updateProgress(totalTransferredBytes, fileSizeBytes);
        }

        long costTime = (System.currentTimeMillis() - time) / 1000;
        System.out.println("\n总耗时：" + costTime +"s");
        fileChannel.close();
        socketChannel.close();
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
