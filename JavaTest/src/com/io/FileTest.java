package com.io;

import java.io.*;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/9/12 21:01
 * description:
 * 1) 用FileOutputStream 在 //当前目录下// 创建一个文件"test.txt"，并向文件输出"HelloWorld"，如果文件已存在，则在原有文件内容后面追加。
 * 2） 用FileInputStream 读入test.txt 文件，并在控制台上打印出test.txt 中的内容。
 * 3） 要求用try-catch-finally 处理异常，并且关闭流应放在finally 块中。
 */
public class FileTest {
	public static void main(String[] args) {
		String fileName = "src.txt";
		String path = "E:" + File.separator + "wz" + File.separator;
		File dir = new File(path);
		if (!dir.isDirectory()) {
			System.out.println("路径不存在，创建路径");
			dir.mkdirs();
		}
		File file = new File(path + fileName);
		createFile(file,null);
		readDataFromExistedFile(file);

		//Create File
		File file1 = new File(dir,"asasa");
		createFile(file1,null);

		//Rename Dir
		//dir.renameTo(new File("E:" + File.separator + "lols" + File.separator));

		//Detele Dir's all files
		boolean success = dir.delete();
		if(!success){
			String[] fileList = dir.list();
			 for (int i=0; i<fileList.length; i++) {
                //boolean isDelete = deleteDir(new File(dir, fileList[i]));
				 System.out.println("遍历文件：" + fileList[i]);
            }
		}


		//Rename File
		//file1.renameTo(new File(dir,"www"));
	}

	/**
	 * 读取文件中的数据，可以一个个的读取  也可以一次性的读取
	 * 一个个读取效率低  所以一次性读取
	 * 当知道文件长度的时候，就
	 * @param file
	 */
	private static void readDataFromExistedFile(File file) {
		try {
			byte[] bytes = new byte[(int) file.length()];
			FileInputStream fins = new FileInputStream(file);
			fins.read(bytes);
			fins.close();
			System.out.println("Read Content :" + new String(bytes));
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	private static void createFile(File file, String path) {
		try {
			FileOutputStream fops = new FileOutputStream(file, true);
			if (!file.exists()) {
				//文件不存在  生成文件，并写入数据
				file.createNewFile();
			}
			fops.write("HelloWorld".getBytes());
			//什么时候flush? 有缓冲的时候？
			//	    fops.flush();
			fops.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	  /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
 			//递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
