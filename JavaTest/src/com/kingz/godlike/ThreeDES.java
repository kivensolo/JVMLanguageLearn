package com.kingz.godlike;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/6/6 11:42 <br>
 * description: 3DES加密 <br>
 */
public class ThreeDES {
    //定义 加密算法,可用 DES,DESede,Blowfish
    private static final String Algorithm = "DESede";

    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try {
            System.out.println("src:"+ src.length);
            DESedeKeySpec keySpec = new DESedeKeySpec(keybyte);
            SecretKey deskey = SecretKeyFactory.getInstance(Algorithm).generateSecret(keySpec);

            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try {
             DESedeKeySpec keySpec = new DESedeKeySpec(keybyte);
            SecretKey deskey = SecretKeyFactory.getInstance(Algorithm).generateSecret(keySpec);
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    //转换成十六进制字符串
    public static String byte2hex(byte[] b) {
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            stmp += String.format("%02X",b[n]);
        }
        return stmp.toLowerCase();
    }

    /**
     * 把16进制字符串转换成字节数组
     *
     * @return byte[]
     */
    //public static byte[] hexStringToByte(String hex) {
    //    byte[] result = new byte[hex.length() / 2];
    //    char[] achar = hex.toCharArray();
    //    for (int i = 0; i < len; i++) {
    //        int pos = i * 2;
    //        result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
    //    }
    //    return result;
    //}

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    public static byte[] hexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}
    private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        //添加新安全算法,如果用JCE就要把它添加进去
        Security.addProvider(new com.sun.crypto.provider.SunJCE());

        String szSrc = "45572$1CEB07909415DA11E007370775BBDEC3$13060090390$3c:da:2a:b1:48:bc$172.31.12.204$3c:da:2a:b1:48:bc$$OTT";
        createMD5(szSrc);
        byte[] encoded = encryptMode("beabafe80000000000000000".getBytes(),szSrc.getBytes());
        String cipherString = byte2hex(encoded);
        System.out.println("加密后HexStr:" + cipherString);
        byte[] bs = hexString2Bytes(cipherString);
        byte[] srcBytes = decryptMode("beabafe80000000000000000".getBytes(), "A8AE90AD4786117783CFF85A6856188AE041BF0C3283EE658A598648B8B0AEC3F7CA103DBEBB1EA894DC88AF70C054C9A4B3C4A2359BA745C0323C8692F0155C2B1570DBF95F91D45546B243D4C703737EEEA78006C2750AC0323C8692F0155C9FD4E29C26E78C6C13099FA9122670F3".getBytes());
        try {
            System.out.println("解密后的字符串:" + srcBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createMD5(String szSrc) throws NoSuchAlgorithmException {
        System.out.println("明文:" + szSrc);
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] md5 = md.digest("123456".getBytes());
        System.out.println("md5:" + byte2hex(md5));
    }

    /**
     * 进行补码
     * @param keyStr
     * @return
     */
    public static byte[] build3DesKey(String keyStr){
        byte[] key = new byte[24];
        byte[] temp;
        for (int i=0;i < 24 ;i++){
            key[i] = 0x30;
        }
        try {
            temp = keyStr.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            temp = keyStr.getBytes();
        }
        System.arraycopy(temp, 0, key, 0, temp.length > 24 ? 24 :temp.length);
        System.out.println("补码后的密钥："+ new String(key));
        return key;
    }

}