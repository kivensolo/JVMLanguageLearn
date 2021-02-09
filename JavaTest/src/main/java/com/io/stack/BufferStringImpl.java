package com.io.stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author: King.Z <br>
 * date:  2018/2/13 15:23 <br>
 * description: XXXXXXX <br>
 */
public class BufferStringImpl extends AbstractBuffer {
    private List<byte[]> list;
    private int currentBufferSize;
    private final Object lockObject = new Object();
    private boolean canChange = false;
    private int deleteSize;

    public BufferStringImpl(int bufferSize) {
        super(bufferSize);
        //this.list = new ArrayList<>(8 * 1024);
        this.list = new ArrayList<>(bufferSize);
        this.deleteSize = bufferSize / 10;
        this.canChange = true;
    }

    public BufferStringImpl(int bufferSize, List<byte[]> list) {
        super(bufferSize);
        this.list = list;
    }


    @Override
    public void write(String b) {
        synchronized(lockObject) {
            if(!canChange){
                return;
            }
            if(b == null || b.length() == 0){
                return;
            }
            pushBuffer(b.length());
            list.add(b.getBytes());
            currentBufferSize += b.length();
            System.out.println("Buffer appent ----> buffer size:" + currentBufferSize);
        }
    }

    @Override
    public void reset() {
        synchronized(lockObject) {
            this.list.clear();
            this.currentBufferSize = 0;
        }
    }

    @Override
    public void pushBuffer(int newLen) {
        synchronized (lockObject) {
            if (currentBufferSize + newLen > bufferSize) {
                System.out.println("缓冲区满  清空当前缓冲区");
                if (dumper != null) {
                    System.out.println("StackMinitor-PushBuffer dump");
                    dumper.dump();
                } else {
                    System.out.println("StackMinitor-PushBuffer clear");
                    currentBufferSize = 0;
                    list.clear();
                }
                //int removeSize = 0;
                //for(Iterator iterator = list.iterator(); removeSize < deleteSize && iterator.hasNext(); iterator.remove()){
                //    byte[] nextByte = (byte[])iterator.next();
                //    if(nextByte != null){
                //        removeSize += nextByte.length;
                //    }
                //}
                //currentBufferSize -= removeSize;
            }
        }
    }

    @Override
    public AbstractBuffer copy() {
         synchronized(lockObject) {
            ArrayList<byte[]> list = new ArrayList<>();
            list.addAll(this.list);
            return new BufferStringImpl(this.currentBufferSize, list);
        }
    }

    public List<byte[]> getList(){
        synchronized (lockObject){
            return list;
        }
    }

    @Override
    public byte[] cover() {
        synchronized(lockObject) {
            if(list.size() == 0){
                return null;
            }else{
                Iterator iterator = this.list.iterator();
                byte[] byteArrayOps = new byte[]{};
                try {
                    for(; iterator.hasNext(); iterator.remove()) {
                        byte[] e = (byte[])iterator.next();
                        if(e != null) {
                            //TODO
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
                return byteArrayOps;
            }
        }
    }

    @Override
    public boolean isOver(String var1) {
        return false;
    }
}
