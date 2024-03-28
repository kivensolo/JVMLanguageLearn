package com.io;

import com.io.utils.FileUtils;

import java.io.*;

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
////		CreateTestFile();
//        encryptFile();
        decryptFile();
    }

    private static void CreateTestFile() {
        String path = FileUtils.concatPaths("E:", "wz", "testFile.txt");
        //FileUtils.createOrExistsDir(path);
        boolean result = FileUtils.createOrExistsFile(path);
        System.out.println("创建结果：" + result);
    }

    private static void encryptFile() {
        File file = new File("JavaTest\\src\\version_info_target.xml");

        try {
            FileOutputStream ops = new FileOutputStream("JavaTest\\src\\version_info_target_encrypted.xml");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                ops.write(encrypt(buffer), 0, bytesRead);
            }
			ops.flush();
			ops.close();
			fileInputStream.close();
        } catch (IOException e) {
			throw new RuntimeException(e);
		}
    }

    private static void  decryptFile() {
        File file = new File("JavaTest\\src\\version_info_target_encrpt_android.xml");
        try {
            FileOutputStream ops = new FileOutputStream("JavaTest\\src\\version_info_target_decrypted_and.xml");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                ops.write(decryptByte(buffer), 0, bytesRead);
            }
			ops.flush();
			ops.close();
			fileInputStream.close();
        } catch (IOException e) {
			throw new RuntimeException(e);
		}
    }



    private static byte[] encrypt(byte[] data) {
        byte[] key = "starcor".getBytes();  // 加密密钥
        for (int i = 0; i < data.length; i++) {
            data[i] ^= key[i % key.length];     // 与密钥进行异或运算加密
            data[i] &= 0xFF;                    // 确保结果在 0-255 范围内
        }
        return data;
    }

    private static byte[] decryptByte(byte[] data) {
        byte[] key = "starcor".getBytes();  // 解密密钥

        for (int i = 0; i < data.length; i++) {
            data[i] &= 0xFF;                    // 确保结果在 0-255 范围内
            data[i] ^= key[i % key.length];     // 与密钥进行异或运算解密
        }
        return data;
    }
}
