package com.kingz.godlike.utils;

import java.io.UnsupportedEncodingException;

public class Base64Utils {

    private static char[] base64EncodeChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] base64DecodeChars = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static String encode(byte[] paramArrayOfByte) {
        StringBuilder localSb = new StringBuilder();
        int length = paramArrayOfByte.length;
        int index = 0;
        while (index < length) {
            int byte1 = paramArrayOfByte[(index++)] & 0xFF;
            char v1 = base64EncodeChars[byte1 >>> 2]; //6bit + 前补2个bit的0
            if (index == length) {
                //还差2个字符 使用v1结尾的2位数据作为高位,并补零
                char v2 = base64EncodeChars[(byte1 & 0x03) << 4];
                localSb.append(v1);
                localSb.append(v2);
                localSb.append("==");
                break;
            }
            int byte2 = paramArrayOfByte[(index++)] & 0xFF;
            char v2 = base64EncodeChars[(byte1 & 0x03) << 4 | ((byte2 & 0xF0) >>> 4)];
            if (index == length) {
                //还差1个字符
                char v3 = base64EncodeChars[(byte2 & 0x0f) << 2];
                localSb.append(v1);
                localSb.append(v2);
                localSb.append(v3);
                localSb.append("=");
                break;
            }
            int byte3 = paramArrayOfByte[(index++)] & 0xFF;
            char v3 = base64EncodeChars[(byte2 & 0x0F) << 2 | ((byte3 & 0xC0) >>> 6)];//byte2后4bit+byte3后2bit
            char v4 = base64EncodeChars[byte3 & 0x3F];
            localSb.append(v1);
            localSb.append(v2);
            localSb.append(v3);
            localSb.append(v4);
        }

        return localSb.toString();
    }

    public static byte[] decode(String paramString) throws UnsupportedEncodingException {
        StringBuffer localStringBuffer = new StringBuffer();
        byte[] arrayOfByte = paramString.getBytes("US-ASCII");
        int i = arrayOfByte.length;
        int j = 0;
        while (j < i) {
            int k;
            do {
                k = base64DecodeChars[arrayOfByte[(j++)]];
            } while ((j < i) && (k == -1));
            if (k == -1) {
                break;
            }
            int m;
            do {
                m = base64DecodeChars[arrayOfByte[(j++)]];
            } while ((j < i) && (m == -1));
            if (m == -1) {
                break;
            }
            localStringBuffer.append((char) (k << 2 | (m & 0x30) >>> 4));
            int n;
            do {
                n = arrayOfByte[(j++)];
                if (n == 61) {
                    return localStringBuffer.toString().getBytes("ISO-8859-1");
                }
                n = base64DecodeChars[n];
            } while ((j < i) && (n == -1));
            if (n == -1) {
                break;
            }
            localStringBuffer.append((char) ((m & 0xF) << 4 | (n & 0x3C) >>> 2));
            int i1;
            do {
                i1 = arrayOfByte[(j++)];
                if (i1 == 61) {
                    return localStringBuffer.toString().getBytes("ISO-8859-1");
                }
                i1 = base64DecodeChars[i1];
            } while ((j < i) && (i1 == -1));
            if (i1 == -1) {
                break;
            }
            localStringBuffer.append((char) ((n & 0x3) << 6 | i1));
        }
        return localStringBuffer.toString().getBytes("ISO-8859-1");
    }
}
