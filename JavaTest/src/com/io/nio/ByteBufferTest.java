package com.io.nio;

import java.nio.ByteBuffer;

public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(64);
        bf.put((byte) '0');
        bf.putInt(1);
        bf.putInt(0);
        bf.flip();
        bf.get();
        bf.get();
        System.out.println(bf.getInt());
    }
}
