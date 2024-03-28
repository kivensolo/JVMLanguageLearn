package com.nio.buffer;

import java.nio.ByteBuffer;

/**
 * NIO中主要有八种缓冲区类:
 * - Byte/Char/Short/Int/Long/Float/DoubleBuffer
 * - MappedByteBuffer专门用于内存映射的一种byteBuff
 *
 * 所有缓冲去有四个属性：
 * - capacity   最大容量，在创建时就固定死了，不可改变;
 * - limit      缓冲区的有效极限终点，不能在缓冲区中对超过极限的位置进行读写操作。(可以被修改);
 * - position   下一个要被读、写的元素索引，每次读写缓冲区数据时都会改变值;
 * - mark       标记，可以调用mark()来设置mark=position, （标记当前的读写位置），
 *              调用reset()可以让position恢复到标记的位置
 * 并遵循：mark <= position <= limit <= capacity
 *
 *
 * 更多方法详解：https://blog.csdn.net/xialong_927/article/details/81044759
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        flipUse();
    }

    /**
     * java.nio.Buffer类是一个抽象类，不能被实例化。
     * Buffer类的直接子类，如ByteBuffer等也是抽象类，所以也不能被实例化。
     * 但是ByteBuffer类提供了4个静态工厂方法来获得ByteBuffer的实例：
     */
    private static void instanceTest() {
        System.out.println("----------Test allocate--------");
        System.out.println("before alocate:" + Runtime.getRuntime().freeMemory());
        /*
         * 从堆空间中分配一个容量大小为capacity的byte数组作为缓冲区的byte数据存储器
         * position为0，limit = capacity，mark不确定（默认-1）
         */
        ByteBuffer buffer = ByteBuffer.allocate(500 * 1024);
        System.out.println("buffer = " + buffer);
        System.out.println("after  alocate:" + Runtime.getRuntime().freeMemory());
        /*
         * 不使用JVM堆栈而是通过操作系统来创建内存块用作缓冲区，
         * 它与当前操作系统能够更好的耦合，因此能进一步提高I/O操作速度。
         *
         * 但是分配直接缓冲区的系统开销很大，因此只有在缓冲区较大并长期存在，
         * 或者需要经常重用时，才使用这种缓冲区。
         */
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(10 * 1024);
        System.out.println("directBuffer = " + directBuffer);
        System.out.println("after direct alocate:" + Runtime.getRuntime().freeMemory());

        System.out.println("----------Test wrap--------");
        /*
         * 将字节数组包装到缓冲区中，bytes数组或buff缓冲区任何一方中数据的改动都会影响另一方。
         *
         * 其实ByteBuffer底层本来就有一个bytes数组负责来保存buffer缓冲区中的数据，
         * 通过allocate方法系统会帮你构造一个byte数组
         */
        buffer = ByteBuffer.wrap(new byte[32]);
        System.out.println(buffer);
        /*
         * 在上一个方法的基础上可以指定偏移量和长度，
         * @parms offset 包装后byteBuffer的position，
         * @parms length  limit-position的大小，
         *
         * 从而我们可以得到limit的位置为length+position(offset)
         */
         buffer = ByteBuffer.wrap(new byte[32],10,10);
         System.out.println(buffer);
    }

    /**
     * 常用方法
     */
    void methods(){
        ByteBuffer buffer = ByteBuffer.allocate(20);
        buffer.limit();//获取limit
        buffer.limit(15); //设置limit
        buffer.reset(); // 把position退回至mark点
        // 翻转，也就是让flip之后的position到limit这块区域变成之前的0到position这块，
        // 翻转就是将一个处于存数据状态的缓冲区变为一个处于准备取数据的状态
        buffer.flip();
        buffer.rewind();//把position设为0，mark设为-1，不改变limit的值
        buffer.remaining();//return limit - position; 返回limit和position之间相对位置差（还有"干净区域"）


        buffer.get(); //相对读，从position位置读取一个byte，并将position+1，为下次读写作准备
        buffer.get(2); //绝对读，读取byteBuffer底层的bytes中下标为index的byte，不改变position
        //buffer.get(byte[] dst, int offset, int length); //从position位置开始相对读，读length个byte，并写入dst下标从offset到offset+length的区域

        buffer.put((byte) 0x01); //相对写，向position的位置写入一个byte，并将postion+1，为下次读写作准备
        buffer.put(0,(byte) 2); //绝对写，向byteBuffer底层的bytes中下标为index的位置插入byte b，不改变position
        //buffer.put(ByteBuffer src)    用相对写，把src中可读的部分（也就是position到limit）写入此byteBuffer
        //buffer.put(byte[] src, int offset, int length) 从src数组中的offset到offset+length区域读取数据并使用相对写写入此byteBuffer
    }

    private static void flipUse() {
        ByteBuffer bf = ByteBuffer.allocate(64); //limit = capacity == 64  pos = 0
        bf.put((byte) '0');
        bf.putInt(1);
        bf.putInt(0); // pos = 9   ['0',0,0,0,1,0,0,0,0,.......]
        bf.flip();    // lim = pos = 9   pos = 0  mark=-1
        bf.get();     // pos = 1
        bf.get();     // pos = 2
        System.out.println(bf.getInt());//0100 ===> 16^2+0+0=256
    }
}
