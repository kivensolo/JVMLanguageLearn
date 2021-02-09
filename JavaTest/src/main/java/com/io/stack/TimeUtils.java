package com.io.stack;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: King.Z <br>
 * date:  2018/2/13 15:00 <br>
 * description: XXXXXXX <br>
 */
public class TimeUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    private static long TimeDelta = System.currentTimeMillis() - timestamp();

    public static long currentTimeMillis() {
        return timestamp() + TimeDelta;
    }

    private static long timestamp() {
        return System.nanoTime() / 1000000L;
    }

    public static String getCurrentTime() {
        try {
            return format.format(new Date(currentTimeMillis()));
        } catch (Exception var1) {
            var1.printStackTrace();
            return null;
        }
    }
}
