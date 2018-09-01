package com.kingz.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.WeakHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * author: King.Z <br>
 * date:  2018/8/28 21:12 <br>
 * description: 对jar包的文件进行文件条目信息获取并记录，对整体信息做md5的加密。
 * 可作为压缩包文件的md5校验。 <br>
 */
public class ZipMd5Test {


    public static void main(String[] args) {
        try {
            String path = new File("JavaTest\\lib\\commons-lang.jar").getAbsolutePath();
            final ZipFile zipFile = new ZipFile(path);
			final Enumeration<? extends ZipEntry> entries = zipFile.entries();
			StringBuilder jarInfo = new StringBuilder();
			jarInfo.append("size:");
			jarInfo.append(zipFile.size());
			jarInfo.append("\n");
			jarInfo.append("\n");
			while (entries.hasMoreElements()) {
				final ZipEntry zipEntry = entries.nextElement();
				jarInfo.append("entry:");
				jarInfo.append(zipEntry.getName());
				jarInfo.append("\n  size:");
				jarInfo.append(zipEntry.getSize());
				jarInfo.append("\n   crc:");
				jarInfo.append(zipEntry.getCrc());
				jarInfo.append("\n");
			}
			zipFile.close();
            String md5 = calMD5(jarInfo.toString());
            System.out.println("md5 = " + md5);
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private static WeakHashMap<Thread, MessageDigest> _threadHashMap = new WeakHashMap<Thread, MessageDigest>();

    public static String calMD5(String imageKey) {
		String localCacheKey = calMD5(imageKey.getBytes());
		if (localCacheKey == null) {
			return imageKey;
		}
		return localCacheKey;
	}

    private static MessageDigest getMD5() {
        synchronized (_threadHashMap) {
            Thread thread = Thread.currentThread();
            MessageDigest messageDigest = _threadHashMap.get(thread);
            if (messageDigest == null) {
                try {
                    MessageDigest md5 = MessageDigest.getInstance("md5");
                    _threadHashMap.put(thread, md5);
                    return md5;
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            messageDigest.reset();
            return messageDigest;
        }
    }

    public static String calMD5(byte[] data) {
        String localCacheKey = null;
        MessageDigest md5 = getMD5();
        byte[] digest = md5.digest(data);
        StringBuilder val = new StringBuilder();

        for (byte b : digest) {
            val.append(String.format("%02X", b & 0x00FF));
        }
        localCacheKey = val.toString();
        return localCacheKey;
    }
}

