package com.io;

import java.io.*;

/**
 * description:
 *
 * 常用方法：
 * isDirectory()：方法是判断文件是文件夹还是一个文件
 * File.speparator：判断在不同的系统中斜杠的方向，
 *      在windows中斜杠的方向是向右斜的\   在Linux中斜杠的方向是向左斜的/
 * File.pathSeparator：“；”
 */
public class ByteStreamSample{
    static File file;
    static String path;
    static String data = "codeWorld 逗比";
    static InputStream ins;
    static OutputStream ous;

    public static void main(String[] args) throws Exception {
        String filePath = "e:" + File.separator + "file" + File.separator + "1213";

        //show_XDiskListDir("D:" + File.separator);
        CreateNewFile(filePath,"IoTest.txt");
        writeDataToFile(data);
        appendNewDataToFile(file,"  SomeData   ");
        readDataOneByOne(file, (int) file.length());
        readDataFromFile(file, (int) file.length());
    }

    /**
     * 列出指定目录的全部文件
     * @param x_Disk
     */
    public static void show_XDiskListDir(String x_Disk){
        File f = new File(x_Disk);
        String[]  str = f.list();
        File[]  files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }
    }
    /**
     * 追加新内容
     * @param mFile  目标文件
     * @param data   追加内容
     */
    public static void appendNewDataToFile(File mFile,String data){
        System.out.println("AppendNewContent");

        try {
            ous = new FileOutputStream(mFile,true);
            ous.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写数据
     * @param content 数据内容
     */
    public static void writeDataToFile(String content){
        System.out.println("Write Data By byteStream.");
        try {
            ous = new FileOutputStream(file); //默认不追加
            ous.write(content.getBytes());
            ous.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新建文件
     * @param filePath     文件路径
     * @param fileName     文件名
     * @throws Exception
     */
    public static void CreateNewFile(String filePath,String fileName) throws Exception {
        path = filePath;
        file = new File(path);
        //判断路径中的文件夹存在不
        if (!file.isDirectory()) {
            System.out.println("路径不存在，创建路径");
            file.mkdirs();
        }
        //判断文件是否在指定路径可以找到
        file = new File(path + File.separator + fileName);
        if (!file.exists() || file.isDirectory()) {
            System.out.println("路径已存在但文件不存在，新建文件");
            file.createNewFile();
            System.out.println("该分区总大小:" + file.getTotalSpace() / (int)Math.pow(1024,3) + "G");
            System.out.println("该分区剩余大小:" + file.getFreeSpace() / (int)Math.pow(1024,3) + "G");
            System.out.println("文件名  " + file.getName());  //  返回由此抽象路径名表示的文件或目录的名称。
            System.out.println("文件父目录路径 " + file.getParent());// 返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回 null。
            return;
        }
        System.out.println("Dir and File is exists.");
    }

    /**
     * 从文件中读取数据-----一次性
     * @param mFile         目标文件
     * @param bufferLen     缓冲区大小
     */
    public static void readDataFromFile(File mFile,int bufferLen){
        //指导文件多大
        byte[] buffer = new byte[bufferLen]; //1024的话长度可能超长
        //不知道文件多少大
        //byte[] buffer = new byte[1024]; //1024
        try {
            ins = new FileInputStream(mFile);
            //byte[] buffer = new byte[ins.available()]; //定义一个刚刚好的换从区域
            ins.read(buffer);
            ins.close();
            System.out.println("Read Content :"+new String(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 从文件中读取数据-----一个个的
     * @param mFile         目标文件
     * @param bufferLen     缓冲区大小
     */
    public static void readDataOneByOne(File mFile,int bufferLen){
        byte[] buffer = new byte[bufferLen]; //1024的话长度可能超长
        try {
            ins = new FileInputStream(mFile);
            for (int i = 0; i < buffer.length; ++i) {
                buffer[i] = (byte) ins.read();
            }
            System.out.println("Read Content One By One :"+new String(buffer) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

