package com.auth;

import java.nio.charset.StandardCharsets;

public class HuaWeiAuthTest {
    /**
     *
     * 用于 Authentication_Initial.REQ 流程
     * 测试 response 参数计算
     */
    public static void main(String[] args) {
    // <editor-fold defaultstate="collapsed" desc="现网机顶盒采集数据">
        String realm = "20001093";
//        String uri = "/EPG/XML/authentication_initial";
        String uri = "/EDS/pub/authentication_initial";
        String nonce = "3136303638";
        String nc = "000000001";
        String cnonce = "ce8347440901aa4112f77f86bdcf6a58";
        String qop = "auth-int";
        String method = "POST";
        String body = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><AuthenticationRequest><STBID>009913FF6001430023046C26369CC49B</STBID></AuthenticationRequest>";

        String username = "ysttest685";
        String password = "123123";
    // </editor-fold>


       // <editor-fold defaultstate="collapsed" desc="Response参数计算">
        //计算规则：MD5 [MD5( username: realm: password): nonce: nc: cnonce: qop: MD5(method: URI：MD5（entity-body）)]
        String encodePwd = encode(password.getBytes(StandardCharsets.UTF_8));
        String a1 = username + ":" + realm + ":" + encodePwd;
        String a1Md5 = Md5Utils.calMD5(a1).toLowerCase();

        String a2 = method + ":" + uri;
        //noinspection ConstantValue
        if ("auth-int".equals(qop)) {
            a2 = a2 + ":" + Md5Utils.calMD5(body).toLowerCase();
        }
        String a2Md5 = Md5Utils.calMD5(a2).toLowerCase();

        /**
         * a1Md5 = 3422a53b18725722058920ed8498c154
         *
         * a2Md5 = fea3546a30cfa0a7e823026a46d99f7a
         *
         * 3422a53b18725722058920ed8498c154:3136303638:000000001:ce8347440901aa4112f77f86bdcf6a58:auth-int:fea3546a30cfa0a7e823026a46d99f7a
         * 7d56a407a38581b36819d842dc5df8d2:3136303638:000000001:ce8347440901aa4112f77f86bdcf6a58:auth-int:fea3546a30cfa0a7e823026a46d99f7a
         */
        String responseBefore = a1Md5 + ":" + nonce + ":" + nc + ":" + cnonce + ":" + qop  + ":" + a2Md5;
        String responseMD5 = Md5Utils.calMD5(responseBefore).toLowerCase(); //实际值: f827382fd2c8f1e83cf69f52051f42d0
        //期望值为: 3adeb06a4b9825716f6eb27ed91668d7
        System.out.println("response:" + responseMD5);
       // </editor-fold>

        //密码未做BASE64: a9d97522ecd7a53e3b523e7f1fe3051d
    }



    private static char[] base64EncodeChars = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'
    };

    private static String encode(byte[] paramArrayOfByte) {
        StringBuffer localStringBuffer = new StringBuffer();
        int i = paramArrayOfByte.length;
        int j = 0;
        while (j < i) {
            int k = paramArrayOfByte[(j++)] & 0xFF;
            if (j == i) {
                localStringBuffer.append(base64EncodeChars[(k >>> 2)]);
                localStringBuffer.append(base64EncodeChars[((k & 0x3) << 4)]);
                localStringBuffer.append("==");
                break;
            }
            int m = paramArrayOfByte[(j++)] & 0xFF;
            if (j == i) {
                localStringBuffer.append(base64EncodeChars[(k >>> 2)]);
                localStringBuffer.append(base64EncodeChars[((k & 0x3) << 4 | (m & 0xF0) >>> 4)]);
                localStringBuffer.append(base64EncodeChars[((m & 0xF) << 2)]);
                localStringBuffer.append("=");
                break;
            }
            int n = paramArrayOfByte[(j++)] & 0xFF;
            localStringBuffer.append(base64EncodeChars[(k >>> 2)]);
            localStringBuffer.append(base64EncodeChars[((k & 0x3) << 4 | (m & 0xF0) >>> 4)]);
            localStringBuffer.append(base64EncodeChars[((m & 0xF) << 2 | (n & 0xC0) >>> 6)]);
            localStringBuffer.append(base64EncodeChars[(n & 0x3F)]);
        }
        return localStringBuffer.toString();
    }
}
