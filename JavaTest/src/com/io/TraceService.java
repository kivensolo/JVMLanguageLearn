package com.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * author: King.Z <br>
 * date:  2018/2/13 10:22 <br>
 * description: 堆栈采集 <br>
 */
public class TraceService {

    //initial buffer size is 8kb
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8 * 1024);

    public static TraceService mInstance;
      String fileName = "log.txt";
        String path = "E:" + File.separator + "wz" + File.separator;
    private TraceService() {

    }


    public TraceService getInstance(){
        if(mInstance == null){
            mInstance = new TraceService();
        }
        return mInstance;
    }

    public void readStack() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        byte[] stackBytes = Arrays.toString(stackTrace).getBytes();
        try {
            byteArrayOutputStream.write("------ Time:".concat(String.valueOf(System.currentTimeMillis()).concat("\n")).getBytes());
            byteArrayOutputStream.write(stackBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dumpStack() {
        try {
            File dir = new File(path);
            if (!dir.isDirectory()) {
                System.out.println("路径不存在，创建路径");
                dir.mkdirs();
            }
            File logFile = new File(path.concat(fileName));
            FileOutputStream fileOutputStream = new FileOutputStream(logFile,true);

            byteArrayOutputStream.flush();
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void resetStack() {
        byteArrayOutputStream.reset();
    }

}
