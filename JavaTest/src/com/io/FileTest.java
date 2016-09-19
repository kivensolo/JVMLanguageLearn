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
		String path = "E:" + File.separator + "KKKKK";
		System.out.println("路径="+path);
		File file = new File(path);
		if (!file.isDirectory()) {
			System.out.println("路径不存在，创建路径");
			file.mkdirs();
		}
		String filePath = path + File.separator + "test.txt";
		file = new File(filePath);
		createFile(file, path);
		readDataFromFile(file);
	}

	/**
	 * 读取文件中的数据，可以一个个的读取  也可以一次性的读取
	 * 一个个读取效率低  所以一次性读取
	 * 当知道文件长度的时候，就
	 * @param file
	 */
	private static void readDataFromFile(File file) {
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
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
