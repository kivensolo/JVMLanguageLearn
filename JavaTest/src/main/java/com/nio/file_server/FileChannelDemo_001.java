package com.nio.file_server;

import com.io.utils.FileIOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo_001 {
    public static void main(String[] args){
        byte[] bytes = FileIOUtils.readFile2BytesByChannel(".\\JavaTest\\src\\main\\java\\com\\nio\\readMe.md");
        String content = new String(bytes);
        System.out.println(content);

//        RandomAccessFile randomAccessFile = null;
//        try {
//            randomAccessFile = new RandomAccessFile(".\\JavaTest\\src\\main\\java\\com\\nio\\readMe.md", "rw");
//            FileChannel channel = randomAccessFile.getChannel();
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//            int readNum = channel.read(buffer);
//            while (readNum != -1){
//                System.out.println("read Data============================:" + readNum);
//                buffer.flip();
//                while (buffer.hasRemaining()){
//                    System.out.print((char)buffer.get());
//                }
//                buffer.clear();
//                readNum = channel.read(buffer);
//            }
//            randomAccessFile.close();
//            System.out.println("Read FIN.");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}
