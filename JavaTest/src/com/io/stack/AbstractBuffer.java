package com.io.stack;

/**
 * author: King.Z <br>
 * date:  2018/2/13 14:39 <br>
 */
public abstract class AbstractBuffer {
    protected StackMinitor.StackDumper dumper;
    protected int bufferSize;
    protected static final String RETURN_STR = "%s\n";

    public AbstractBuffer(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public abstract void write(String b);
    public abstract void reset();

    public abstract void pushBuffer(int newLen);

    public abstract AbstractBuffer copy();

    public abstract byte[] cover();

    public abstract boolean isOver(String var1);
}
