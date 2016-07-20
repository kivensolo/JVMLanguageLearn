package com.io;

import java.io.*;

/**
 * description:
 * isDirectory()方法是判断文件是文件夹还是一个文件
 * File.speparator的作用是判断在不同的系统中斜杠的方向，
 * 在windows中斜杠的方向是向右斜的\\   在Linux中斜杠的方向是向左斜的//
 */
public class IOFileTest {
    static File file;
    static String path;
    static String data = "aasdas12321";
    static InputStream ins;
    static OutputStream ous;

    public static void main(String[] args) throws Exception {
        CreateNewFile();
        writeDataToFile();
    }

    public static void writeDataToFile(){
        System.out.println("字节流方式写数据~");
        byte[] bytes = new byte[1024];//存储数据的buffer
        try {
            FileInputStream fins = new FileInputStream(file);
            FileOutputStream fous = new FileOutputStream(file);
            while(fins.read(data.getBytes()) != -1){
                System.out.println("读取到："+ (char)fins.read(data.getBytes()));
                fous.write((char)fins.read(data.getBytes()));
            }
            fins.close();
            fous.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CreateNewFile() throws Exception {
         path = "e:" + File.separator + "file" + File.separator + "1213";
        file = new File(path);
        //判断路径中的文件夹存在不
        if (!file.isDirectory()) {
            System.out.println("路径不存在，创建路径");
            file.mkdirs();
        }
        //判断文件是否在指定路径可以找到
        file = new File(path + File.separator + "IoTest.txt");
        if (!file.exists() || file.isDirectory()) {
            System.out.println("路径已存在但文件不存在，新建文件");
            file.createNewFile();
            System.out.println("该分区总大小:" + file.getTotalSpace() / (int)Math.pow(1024,3) + "G");
            System.out.println("该分区剩余大小:" + file.getFreeSpace() / (int)Math.pow(1024,3) + "G");
            System.out.println("文件名  " + file.getName());  //  返回由此抽象路径名表示的文件或目录的名称。
            System.out.println("文件父目录路径 " + file.getParent());// 返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回 null。
            return;
        }
        System.out.println("目录以及文件都已存在。");
    }
}

