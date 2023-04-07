package com.kingz.utils;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

public class CommonUtils {

    public static void closeSafety(Object obj) {
        try {
            if (obj instanceof Process) {
                ((Process)obj).destroy();
            } else if (obj instanceof Closeable) {
                ((Closeable)obj).close();
            }
        } catch (IOException iOException) {}
    }

    public static String objToJsonString(Object obj) {
        if (null == obj)
            return null;
        StringBuilder json = new StringBuilder();
        if (obj instanceof String || obj instanceof Character) {
            json.append("\"").append(obj).append("\"");
        } else if (obj instanceof Collection) {
            json.append("[");
            Collection array = (Collection)obj;
            if (!array.isEmpty()) {
                for (Object subObj : array)
                    json.append(",").append(objToJsonString(subObj));
                json.delete(1, 2);
            }
            json.append("]");
        } else if (obj instanceof Map) {
            json.append("{");
            Map<?, ?> map = (Map<?, ?>)obj;
            if (!map.isEmpty()) {
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    json.append(",\"").append(entry.getKey()).append("\":").append(objToJsonString(entry.getValue()));
                }
                json.delete(1, 2);
            }
            json.append("}");
        } else {
            json.append(obj);
        }
        return json.toString();
    }

    public  static Object parseJson(String jsonStr) {
        if (isEmpty(jsonStr))
            return null;
        try {
            return parseJson(jsonStr.toCharArray(), 0, new int[1]);
        } catch (Exception e) {
            return null;
        }
    }

    private static Object parseJson(char[] json, int off, int[] blockLen) {
        if (null == json || off < 0 || json.length - off < 2)
            return null;
        Object result = null;
        Object k = null, v = null;
        boolean nextStrIsKey = false;
        boolean inStr = false;
        int blockBegin = -1;
        for (int i = off; i < json.length; i++) {
            char ch = json[i];
            if ('"' == ch)
                inStr = !inStr;
            if (' ' != ch || inStr)
                if ('"' == ch && nextStrIsKey) {
                    if (blockBegin > 0) {
                        k = new String(json, blockBegin, i - blockBegin);
                        nextStrIsKey = false;
                        blockBegin = -1;
                    } else {
                        blockBegin = i + 1;
                    }
                } else if (':' == ch && !inStr) {
                    blockBegin = i + 1;
                } else if ('{' == ch || '[' == ch) {
                    if (null == result) {
                        if ('{' == ch) {
                            result = new HashMap<>();
                            nextStrIsKey = true;
                        } else {
                            result = new ArrayList();
                            nextStrIsKey = false;
                            blockBegin = i + 1;
                        }
                    } else {
                        v = parseJson(json, i, blockLen);
                        i += blockLen[0];
                        i--;
                        nextStrIsKey = false;
                        blockBegin = -1;
                    }
                } else if ((',' == ch && !inStr) || '}' == ch || ']' == ch) {
                    if (blockBegin > 0) {
                        int l = blockBegin, r = i - 1;
                        for (; l < r && json[l] <= ' '; l++);
                        for (; l < r && json[r] <= ' '; r--);
                        v = ('"' == json[l]) ? new String(json, l + 1, r - l - 1) : new String(json, l, r - l);
                    }
                    boolean isMap = false;
                    if (result instanceof Map) {
                        ((Map<Object, Object>)result).put(k, v);
                        isMap = true;
                    } else if (result instanceof Collection) {
                        ((Collection)result).add(v);
                    }
                    if (',' == ch) {
                        nextStrIsKey = isMap;
                        blockBegin = nextStrIsKey ? -1 : (i + 1);
                    } else {
                        blockLen[0] = i - off + 1;
                        return result;
                    }
                }
        }
        return result;
    }



    private static Object[] getInetAddress() {
        try {
            for (Enumeration<NetworkInterface> en0 = NetworkInterface.getNetworkInterfaces(); en0.hasMoreElements(); ) {
                NetworkInterface net = en0.nextElement();
                for (Enumeration<InetAddress> en1 = net.getInetAddresses(); en1.hasMoreElements(); ) {
                    InetAddress address = en1.nextElement();
                    if (!address.isLoopbackAddress() &&
                            address.isSiteLocalAddress() && !skipNetworkName(net))
                        return new Object[] { net, address };
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean skipNetworkName(NetworkInterface net) {
        String dn = net.getDisplayName().toLowerCase();
        return (dn.contains("vmware") || dn.contains("virtualbox"));
    }

    public static boolean isWifi() {
        Object[] network = getInetAddress();
        return (null != network && ((NetworkInterface)network[0]).getName().toLowerCase().startsWith("wlan"));
    }

    public static boolean isEmpty(Object obj) {
        if (null == obj)
            return true;
        if (obj instanceof String) {
            String v = ((String)obj).trim();
            return ("".equals(v) || "null".equals(v));
        }
        if (obj instanceof Collection)
            return ((Collection)obj).isEmpty();
        if (obj instanceof Map)
            return ((Map)obj).isEmpty();
        throw new RuntimeException("object class not support.");
    }
}
