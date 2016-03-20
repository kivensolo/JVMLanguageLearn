package com.kingz.godlike;

import java.io.File;

/**
 * description:
 *
 *
 *  isDirectory()方法是判断文件是文件夹还是一个文件
 *  File.speparator的作用是判断在不同的系统中斜杠的方向，
 *  在windows中斜杠的方向是向右斜的\\   在Linux中斜杠的方向是向左斜的//
 *
 *
 */
public class IOFileTest {
    public static void main(String[] args) throws Exception {
        CreateNewFile();
    }

    public static void CreateNewFile() throws Exception {
        String path =  "e:" + File.separator + "file" + File.separator + "1213";
        File file = new File(path);
        //判断路径中的文件夹存在不
        if(!file.isDirectory()){
            System.out.println("路径不存在，创建路径");
            file.mkdirs();
        }
        //判断文件是否在指定路径可以找到
        file = new File(path + File.separator + "IoTest.txt");
        if(!file.exists() || file.isDirectory()){
            System.out.println("路径存在且文件存在：" +  file.createNewFile());
        }
    }
}

