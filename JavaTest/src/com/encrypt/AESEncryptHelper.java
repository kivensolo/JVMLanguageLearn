package com.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptHelper {
	/**
	 * 待加密的url，将需要加密的url赋值给content
	 */
	//private static String content = "http://202.100.170.5:9900/default.aspx";
	private static String content = "http://jswx.starcor.com/webapp_v2/xlzq/gdgd/cs/index.html";
	private static String password = "ef82042aa04c634f62f98ed265dad611";
	public static void main(String[] args) {
		byte[] encryptBytes;
		try {
			encryptBytes = encryptByAes(content, password);
			String encryprString = parseByte2HexStr(encryptBytes);
			System.out.println(encryprString);//打印加密后的结果
			byte[] bytes = parseHexStr2Byte(encryprString);
			String decryptString = decryptByAes(bytes, password);
			System.out.println(decryptString);//打印解密后的结果，用作检验
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//2进制转16进制
	public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
	}

	//16进制转2进制
	public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
                return null;
        }
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
	}

    public static byte[] encryptByAes(String data, String pass) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = padString(data).getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(parseHexStr2Byte(pass), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();

            return new byte[0];
        }
    }

    public static String decryptByAes(byte[] data, String pass) throws Exception {
    	try{
        	Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keyspec = new SecretKeySpec(parseHexStr2Byte(pass), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keyspec);
            byte[] original = cipher.doFinal(data);
            String ret = new String(original);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String padString(String source) {
        char paddingChar = ' ';
        int size = 16;
        int x = source.length() % size;
        int padLength = size - x;
        for (int i = 0; i < padLength; i++) {
            source += paddingChar;
        }
        return source;
    }
}

