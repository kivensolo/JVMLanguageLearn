package com.io.stack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * author: King.Z <br>
 * date:  2018/2/13 17:21 <br>
 * description: XXXXXXX <br>
 */
public class StackMinitor {
    public static int DEFAULT_BUFFER_SIZE = 2 * 1024 * 1024; //2M
    public static int DEFAULT_LOG_FILE_SIZE = 10 * 1024;     //10k
    private static StackMinitor instance;
    private final AbstractBuffer collectBuffer;
    private final AbstractBuffer catchedBuffer;
    public StackHunter mStackHunter;
    public StackDumper mStackDumper;


    private StackMinitor() {
        this.collectBuffer = new BufferStringImpl(DEFAULT_BUFFER_SIZE);
        this.catchedBuffer = new BufferStringImpl(DEFAULT_LOG_FILE_SIZE);
        this.mStackHunter = new StackHunter();
        this.mStackDumper = new StackDumper();
        this.catchedBuffer.dumper = this.mStackDumper;
    }

    public static synchronized StackMinitor getInstance() {
        if (instance == null) {
            instance = new StackMinitor();
        }
        return instance;
    }

    public void start() {
        mStackHunter.start();
    }


    class StackHunter {
        private Thread hunterThread;
        private Boolean isRunning = false;

        public void start() {
            synchronized (collectBuffer) {
                if (!isRunning) {
                    isRunning = true;
                    System.out.println(">>>>>>>>>start stack hunter");
                }
                hunterThread = new Thread("stack-hunter") {
                    @Override
                    public void run() {
                        while (isRunning) {
                            appenData("\n==STACK START == ");
                            appenData("\n== Frame Start:".concat("1111"));
                            appenData("\n== Frame End  :".concat("222"));
                            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                            for (StackTraceElement stack : stackTrace) {
                                appenData("\n".concat(stack.toString()).concat("\r\n"));
                            }
                            try {
                                sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                hunterThread.start();
            }
        }

        private void appenData(String str) {
            synchronized (collectBuffer) {
                collectBuffer.write(str);
            }
        }

        protected void end() {
            this.isRunning = false;
            if (hunterThread != null) {
                hunterThread.interrupt();
            }
        }
    }

    class StackDumper {
        private File stackLogDir;
        private File stackLogFile;
        public StackDumper() {
            String fileName = "log.txt";
            String path = "E:" + File.separator + "wz" + File.separator;
            stackLogDir = new File(path);
            if (!stackLogDir.isDirectory()) {
                System.out.println("路径不存在，创建路径");
                stackLogDir.mkdirs();
            }
            stackLogFile = new File(path.concat(fileName));
            if (!stackLogFile.exists()) {
                try {
                    stackLogFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void dump() {
            System.out.println("dump输出");
            synchronized (collectBuffer) {
                try {
                    FileOutputStream ss = new FileOutputStream(stackLogFile, true);
                    List<byte[]> list = ((BufferStringImpl) collectBuffer).getList();
                    for (byte[] item : list) {
                        ss.write(item);
                    }
                    ss.flush();
                    ss.close();
                    collectBuffer.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    collectBuffer.reset();
                }
            }
        }
    }

}
