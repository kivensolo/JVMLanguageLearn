package com.io;

import com.io.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * author: King.Z
 * date:  2021/12/15 21:01
 * description:
 * Zip文件的生成和解压实现
 * 1. 简单创建zip文件(不添加CRC32校验)
 * 2. 从创建的zip文件中读取所有文件数据
 * 3. 从创建的zip文件中读取指定目录下指定格式的文件数据
 */
public class ZipFileTest {
    private static String zipFilePath = FileUtils.concatPaths("E:", "wz", "simpleZipFile.zip");

    public static void main(String[] args) {
        createSimpleZipFile(false);
        readSimpleZipFile();
        openZipFileWithPathByRegx();
    }

    /**
     * Create zip file.
     * @param update {@code true} refresh old same name file(delate first).
     */
    private static void createSimpleZipFile(boolean update) {
        if (FileUtils.isFileExists(zipFilePath)) {
            if (!update) {
                System.out.println("File is exist!!");
                return;
            } else {
                FileUtils.delete(zipFilePath);
            }
        }
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
            String firstDirPath = FileUtils.concatPaths("innerDir", "Hello", "first");
            String secondDirPath = FileUtils.concatPaths("innerDir", "Hello", "second");
            for (int i = 0; i < 5; i++) {
                String value = "我是第" + i + "条数据";
                byte[] bytes = value.getBytes();
                ZipEntry ze;
                if (i == 4) {
                    ze = new ZipEntry(FileUtils.concatPaths(firstDirPath, "kingz_" + i+ ".bin"));
                } else if(i == 3){
                    ze = new ZipEntry(FileUtils.concatPaths(secondDirPath, "kingz_" + i+ ".bin"));
                }else{
                    //文件条目
                    ze = new ZipEntry("Entry_V" + i + ".bin");
                }
                zos.putNextEntry(ze);
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
            }
            // 模拟往同一目录下写不同数据
            byte[] bytes = "我是第first目录中第二个文件的数据".getBytes();
            ZipEntry ze = new ZipEntry(FileUtils.concatPaths(firstDirPath, "kingz_" + 666 + ".bin"));
            zos.putNextEntry(ze);
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zos != null) {
                try {
                    zos.finish();
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Read all element data from zip file.
     */
    private static void readSimpleZipFile() {
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry element = entries.nextElement();
                if (element.isDirectory()) {
                    continue;
                }
                byte[] data = new byte[(int) element.getSize()];
                InputStream inputStream = zipFile.getInputStream(element);
                inputStream.read(data);
                System.out.println("Dump zip entry name:" + element.getName() + "    ,content:" + new String(data, "UTF-8"));
            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过正则表达式指定读取ZIP包中的数据条目
     */
    private static void openZipFileWithPathByRegx() {
        try {
            String path = "innerDir\\Hello\\second\\";
            InputStream byteArrayInputStream = openZipFileByRegex(path,"kingz_\\d+.bin");
            if(byteArrayInputStream != null){
                byte[] data = new byte[byteArrayInputStream.available()];
                byteArrayInputStream.read(data);
                System.out.println("读取"+path+"目录下kingz_开头的文件:" + new String(data));
            }else{
                System.err.println("未读取到"+path+"目录下kingz_开头的文件。");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized InputStream openZipFileByRegex(String dirPath, String namePattern) {
        Pattern compile = Pattern.compile(namePattern);
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry element = entries.nextElement();
                String elementPath = element.getName();
                if (elementPath.startsWith(dirPath)) {
                    String fileName = elementPath.substring(dirPath.length(), elementPath.length() - 1);
                    if (compile.matcher(fileName).matches()) {
                        return zipFile.getInputStream(element);
                        //byte[] data = new byte[(int) element.getSize()];
                        //InputStream inputStream = zipFile.getInputStream(element);
                        //inputStream.read(data);
                        //return new ByteArrayInputStream(data, 0, data.length);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
