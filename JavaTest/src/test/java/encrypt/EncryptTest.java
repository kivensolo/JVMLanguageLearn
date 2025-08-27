package encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import static org.junit.Assert.assertEquals;

public class EncryptTest {

    @Test
    public void test_JsonMd5AesEncrypt() {
        // JSON内容
        String jsonContent = "{\n" +
                "  \"deviceInfo\": {\n" +
                "    \"deviceId\": \"00391300004222F02402109F476A9151\",\n" +
                "    \"accountIdentity\": \"\",\n" +
                "    \"areacode\": \"\",\n" +
                "    \"cityCode\": \"\",\n" +
                "    \"cpu\": \"armv7_none\",\n" +
                "    \"cpuRate\": \"0.18\",\n" +
                "    \"cpuTemperature\": \"63300\",\n" +
                "    \"cpuTopList\": \"/system/bin/mediaserver(2905):5,/system/bin/audioserver(2851):1,/vendor/bin/hw/android.hardware.audio@2.0-service(2834):1\",\n" +
                "    \"displayType\": \"1\",\n" +
                "    \"imsi\": \"\",\n" +
                "    \"macaddress\": \"10:9f:47:6a:91:51\",\n" +
                "    \"manufacturer\": \"SKYWORTH\",\n" +
                "    \"memTopList\": \"2025236,tv.icntv.ott(3970):259292,system_server(3090):152756,com.iflytek.xiri(6215):147592\",\n" +
                "    \"memUseRate\": \"0.49\",\n" +
                "    \"model\": \"E900V22F\",\n" +
                "    \"release\": \"1.0.0\",\n" +
                "    \"userFlag\": \"\",\n" +
                "    \"userId\": \"\",\n" +
                "    \"version\": \"9\",\n" +
                "    \"wifimacaddress\": \"\"\n" +
                "  },\n" +
                "  \"actualTime\": 4243368,\n" +
                "  \"appLiveList\": [\n" +
                "    {\n" +
                "      \"liveTime\": 600,\n" +
                "      \"packageName\": \"tv.icntv.ott\",\n" +
                "      \"version\": \"V9.3.2.0.YP_HL.23.11.29\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"baseParameterList\": {\n" +
                "    \"area\": \"x045\",\n" +
                "    \"dexVersionCode\": 0,\n" +
                "    \"interfaceVersion\": \"V6.1.0\",\n" +
                "    \"softprobeModel\": 1,\n" +
                "    \"softprobeVersion\": \"SoftDetector_V6.1.0_20220901_37240_37229\",\n" +
                "    \"softprobemanu\": \"1\"\n" +
                "  },\n" +
                "  \"bootTime\": 0,\n" +
                "  \"bootTimeStamp\": 0,\n" +
                "  \"cpInfo\": {\n" +
                "    \"appLive\": \"1\",\n" +
                "    \"cpName\": \"CNTV\",\n" +
                "    \"versionCode\": 0\n" +
                "  },\n" +
                "  \"dhcpInfos\": [],\n" +
                "  \"dhcpv6Infos\": [],\n" +
                "  \"diskUsage\": 23,\n" +
                "  \"epgInfo\": {\n" +
                "    \"avgEpgDownloadCost\": \"66986\",\n" +
                "    \"avgEpgSessionCost\": \"51\",\n" +
                "    \"epgDetailInfoByInterval\": [\n" +
                "      {\n" +
                "        \"endTime\": \"4243368\",\n" +
                "        \"epgDetailInfoByIp\": [\n" +
                "          \"111.40.199.220,0,0,0,2,0,0,1,1,51,1,66986,66986\"\n" +
                "        ],\n" +
                "        \"startTime\": \"3636679\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"epgDetailInfos\": [\n" +
                "      {\n" +
                "        \"avgEpgDownloadCost\": \"66986\",\n" +
                "        \"avgEpgSessionCost\": \"51\",\n" +
                "        \"epgAddr\": \"111.40.199.220\",\n" +
                "        \"epgDwonloadCostHistogram\": \"0,0,0,0,1\",\n" +
                "        \"epgReqCnt\": \"1\",\n" +
                "        \"epgReqReachCnt\": \"1\",\n" +
                "        \"maxEpgDownloadCost\": \"66986\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"epgDwonloadCostHistogram\": \"0,0,0,0,1\",\n" +
                "    \"epgReqCnt\": \"1\",\n" +
                "    \"epgReqReachCnt\": \"1\",\n" +
                "    \"maxEpgDownloadCost\": \"66986\"\n" +
                "  },\n" +
                "  \"eventType\": {\n" +
                "    \"eventCode\": 2,\n" +
                "    \"subEvent\": -1\n" +
                "  },\n" +
                "  \"m3u8Info\": {\n" +
                "    \"avgM3u8SessionCost\": \"0\",\n" +
                "    \"m3u8ReqCnt\": \"0\",\n" +
                "    \"m3u8ReqReachCnt\": \"0\"\n" +
                "  },\n" +
                "  \"md5Lua\": \"1cc1ceda1b27dd1e14e0ba5e1c20b704\",\n" +
                "  \"programInfo\": {\n" +
                "    \"invalidPlayCntList\": \"0\",\n" +
                "    \"invalidPlaySuccessCntList\": \"0\",\n" +
                "    \"joinMulticastCnt\": \"89\",\n" +
                "    \"maxFirstBufferTime\": \"1255\",\n" +
                "    \"multicastTimeoutList\": [\n" +
                "      {\n" +
                "        \"multicastAddr\": \"ff02::1:3\",\n" +
                "        \"timeoutCnt\": 2\n" +
                "      }\n" +
                "    ],\n" +
                "    \"playCnt\": \"1\",\n" +
                "    \"playCntByType\": \"0,1,0;0\",\n" +
                "    \"playDetailInfos\": [\n" +
                "      {\n" +
                "        \"packageName\": \"tv.icntv.ott\",\n" +
                "        \"playCnt\": \"1\",\n" +
                "        \"playSuccessCnt\": \"1\",\n" +
                "        \"version\": \"V9.3.2.0.YP_HL.23.11.29\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"playSuccessCnt\": \"1\",\n" +
                "    \"playSuccessCntByType\": \"0,1,0;0\",\n" +
                "    \"quitMulticastCnt\": \"4\",\n" +
                "    \"seekCnt\": \"0,0,0;0\",\n" +
                "    \"seekSuccessCnt\": \"0,0,0;0\",\n" +
                "    \"sumPlaytime\": \"605147\"\n" +
                "  },\n" +
                "  \"reportMissInfo\": \"0,0,0\",\n" +
                "  \"screenBlurredInfo\": {\n" +
                "    \"screenBlurredAllCnt\": \"0\",\n" +
                "    \"screenBlurredAllTime\": \"0\"\n" +
                "  },\n" +
                "  \"secondActualTime\": 1717655837489,\n" +
                "  \"secondReportAddress\": \"http://111.63.81.139:8080/family/\",\n" +
                "  \"secondReportStatus\": \"2\",\n" +
                "  \"secondTimeStamp\": 1717652238678,\n" +
                "  \"stbPlayingStatus\": \"1\",\n" +
                "  \"stbRunTime\": 4243368,\n" +
                "  \"stutterInfo\": {\n" +
                "    \"shutterType\": 0,\n" +
                "    \"stutterAllCnt\": \"0\",\n" +
                "    \"stutterAllTime\": \"0\"\n" +
                "  },\n" +
                "  \"systemInfo\": {\n" +
                "    \"accessMethod\": \"1\",\n" +
                "    \"dns\": \"61.139.2.69\",\n" +
                "    \"frequency\": \"\",\n" +
                "    \"gateway\": \"172.31.8.1\",\n" +
                "    \"gatewayMac\": \"F8:4A:BF:EC:32:AB\",\n" +
                "    \"ipv4Addr\": \"172.31.9.37\",\n" +
                "    \"ipv6Addr\": \"hsjKGjtsVETohdAAXwntqzZstF4BcmmNCtv0yXUuOIfOZRl60PoST5SbiWjYTyYZ\",\n" +
                "    \"ipv6Dns\": \"\",\n" +
                "    \"linkSpeed\": \"-1\",\n" +
                "    \"networkId\": \"-1\",\n" +
                "    \"networkSpeed\": \"100\",\n" +
                "    \"ssid\": \"-1\",\n" +
                "    \"wirelessSignalStrength\": \"-9999\"\n" +
                "  },\n" +
                "  \"tcpConnectInfo\": {\n" +
                "    \"avgTcpConnectCost\": \"53.72\",\n" +
                "    \"avgTcpretran\": \"0.00\",\n" +
                "    \"tcpConnectCnt\": \"46\",\n" +
                "    \"tcpConnectReachCnt\": \"25\",\n" +
                "    \"tcpDetailInfos\": [\n" +
                "      \"112.54.201.207,10,0,0,0,0,4366,0\",\n" +
                "      \"223.87.23.177,1,0,0,0,0,518,0\",\n" +
                "      \"47.92.101.1,1,1,48,3,0,766,442\",\n" +
                "      \"114.114.114.114,2,0,0,0,0,814,0\",\n" +
                "      \"111.40.199.72,3,3,61,12,0,3094,1494\",\n" +
                "      \"2409:8c28:e00:0:a004:20:240:17,0,0,0,26,0,8130,3445\",\n" +
                "      \"2409:8c28:e00:0:b002:19:240:42,0,0,0,20,0,2030,2030\",\n" +
                "      \"112.13.96.149,1,1,40,4,0,863,3580\",\n" +
                "      \"111.40.198.68,1,1,66,4,0,1845,403\",\n" +
                "      \"111.40.198.23,3,3,57,14,0,10177,1329\",\n" +
                "      \"111.40.198.118,1,1,52,5,0,1879,533\",\n" +
                "      \"111.63.81.139,3,3,31,16,0,19556,1653\",\n" +
                "      \"49.232.125.122,1,1,41,7,0,1722,838\",\n" +
                "      \"39.134.65.106,1,1,56,3,0,1105,945\",\n" +
                "      \"111.40.199.173,1,1,57,5,0,1564,4282\",\n" +
                "      \"2409:8c28:202:1b:2424::67,2,0,0,0,0,1034,0\",\n" +
                "      \"111.40.202.37,2,2,61,9,0,1679,2193\",\n" +
                "      \"111.40.198.79,1,1,57,5,0,1710,567\",\n" +
                "      \"111.40.198.129,1,1,56,9,0,5261,1227\",\n" +
                "      \"111.40.195.82,1,1,57,12,0,15957,2609\",\n" +
                "      \"112.54.201.181,4,0,0,0,0,1776,0\",\n" +
                "      \"39.135.133.149,3,3,63,75765,9,1798791,152428239\",\n" +
                "      \"111.40.199.220,1,1,50,5,0,1440,880\",\n" +
                "      \"61.139.2.69,2,0,0,0,0,814,0\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"trafficInfo\": {\n" +
                "    \"v4Traffic\": 441220716,\n" +
                "    \"v6Traffic\": 919747\n" +
                "  },\n" +
                "  \"tsInfo\": {\n" +
                "    \"avgTsSessionCost\": \"77\",\n" +
                "    \"maxCdnDownloadThroughput\": \"4412766.00\",\n" +
                "    \"tsReqCnt\": \"5\",\n" +
                "    \"tsReqReachCnt\": \"5\"\n" +
                "  },\n" +
                "  \"unloadInfo\": {\n" +
                "    \"unloadAllCnt\": \"0\",\n" +
                "    \"unloadAllTime\": \"0\"\n" +
                "  },\n" +
                "  \"voiceRegInfo\": {\n" +
                "    \"avgSessionCost\": 0,\n" +
                "    \"cnt\": 0,\n" +
                "    \"successCnt\": 0\n" +
                "  },\n" +
                "  \"addressType\": \"0\"" +
                "}"; // 这里填写你的JSON字符串

        // 计算MD5
        String md5Hash = DigestUtils.md5Hex(jsonContent);
        System.out.println("MD5 Hash: " + md5Hash);

        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpe = new SecretKeySpec("cmcc_softdetector".getBytes(), "AES");

//            SecretKey des = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec("cmcc_softdetector".getBytes()));
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpe,  new IvParameterSpec("0000000000000000".getBytes()));

            // 需要将MD5摘要转换为字节数组
            byte[] md5Bytes = md5Hash.getBytes(StandardCharsets.UTF_8);

            // 加密
            byte[] encryptedBytes = cipher.doFinal(md5Bytes);

            // 结果通常需要Base64编码以便于显示和传输
            String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);

            System.out.println("Encrypted MD5 (Base64): " + encryptedBase64);


            assertEquals("g1PY8DEJBYadmPsRpkm2Mw==", encryptedBase64);

        } catch (IllegalBlockSizeException |
                 BadPaddingException |
                 NoSuchPaddingException |
                 NoSuchAlgorithmException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }
}

