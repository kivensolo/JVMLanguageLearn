package com.nio.xul_server.utils;

public class RecycleOutputBuffer {

    private static XulSimpleStack<XulRecycleOutputBuffer> _downloadBufferList = new XulSimpleStack<XulRecycleOutputBuffer>(16);


    public static class XulRecycleOutputBuffer extends XulMemoryOutputStream {
        public XulRecycleOutputBuffer(int initialCapacity) {
            super(initialCapacity);
        }

        @Override
        public void onClose() {
            try {
                recycleDownloadBuffer(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    synchronized public static XulRecycleOutputBuffer obtain(int initialCapacity) {
        XulRecycleOutputBuffer buf = _downloadBufferList.pop();
        if (buf == null) {
            return new XulRecycleOutputBuffer(initialCapacity);
        }
        buf.reset(initialCapacity);
        return buf;
    }

    synchronized private static void recycleDownloadBuffer(XulRecycleOutputBuffer buf) {
        _downloadBufferList.push(buf);
    }
}
