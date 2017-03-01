package com.kingz.jni;

/**
 * author: King.Z <br>
 * date:  2017/3/1 11:30 <br>
 * description: JNI_Demo <br>
 */
public class JniDemo {

    //调用本地方法
    public native static int getNum();

    public native static void setNum(int i);

    public native static void setStr(String str);

    public native static String getStr();

    public static void main(String[] args) {
        System.loadLibrary("JniDemo");
        setNum(100);
        System.out.println("getNum = " + getNum());
    }
}
