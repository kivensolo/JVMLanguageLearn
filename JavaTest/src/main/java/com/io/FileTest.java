package com.io;

import com.io.utils.FileUtils;

/**
 * author: King.Z
 * date:  2016/9/12 21:01
 * description:
 * 1) 用FileOutputStream 在 //当前目录下// 创建一个文件"test.txt"，并向文件输出"HelloWorld"，如果文件已存在，
 * 则在原有文件内容后面追加。
 * 2） 用FileInputStream 读入test.txt 文件，并在控制台上打印出test.txt 中的内容。
 * 3） 要求用try-catch-finally 处理异常，并且关闭流应放在finally 块中。
 */
public class FileTest {
	public static void main(String[] args) {
		CreateTestFile();
	}

	private static void CreateTestFile() {
		String path = FileUtils.concatPaths("E:","wz","testFile.txt");
		//FileUtils.createOrExistsDir(path);
		boolean result = FileUtils.createOrExistsFile(path);
		System.out.println("创建结果：" + result);
	}
}
