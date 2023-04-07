package com.kingz.net.udtcp.multicast;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DataPackage {
    static final int VERSION = 1;

    private static final byte[] HEADER = "KingZ-H".getBytes();
    private static final int HEADER_LEN = HEADER.length;
    private static final int CONTENT_MAX_LEN = 512;

    /**
     * +---------+----------+-------------+------------------+
     * + HEADER  + VERSION  + CONTENT_LEN + Conent(512bytes) +
     * +---------+----------+-------------+------------------+
     * + KingZ-H + short    +  short      +   string max     +
     * +---------+----------+-------------+------------------+
     */
    static final int DATA_LEN = HEADER_LEN + 2 + 2 + CONTENT_MAX_LEN;

    static byte[] createBuf() {
        return new byte[DATA_LEN];
    }

    short version = 0;
    short len = 0;
    byte[] data = null;

    boolean decodeFromBytes(byte[] buf) {
        int offset = 0;
        //头数据校验
        for (; offset < HEADER_LEN; offset++) {
            if (buf[offset] != HEADER[offset])
                return false;
        }
        this.version = (short)((buf[offset] & 0xFF) << 8 | buf[offset + 1] & 0xFF);
        offset += 2;
        this.len = (short)((buf[offset] & 0xFF) << 8 | buf[offset + 1] & 0xFF);
        offset += 2;
        if (this.len > 0) {
            this.len = (short)Math.min(this.len, CONTENT_MAX_LEN);
            this.data = new byte[this.len];
            System.arraycopy(buf, offset, this.data, 0, this.len);
        }
        return true;
    }

    boolean encodeToBytes(byte[] buf) {
        if (buf.length < DATA_LEN)
            return false;
        System.arraycopy(HEADER, 0, buf, 0, HEADER_LEN);
        int offset = HEADER_LEN;
        buf[offset++] = (byte)(version >> 8 & 0xFF);
        buf[offset++] = (byte)(version & 0xFF);
        buf[offset++] = (byte)(len >> 8 & 0xFF);
        buf[offset++] = (byte)(len & 0xFF);
        if (this.len > 0) {
            System.arraycopy(data, 0, buf, offset, Math.min(this.len, CONTENT_MAX_LEN));
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("KingZ-H%d%d%s", HEADER, version, len, new String(data, StandardCharsets.UTF_8));
    }
}
