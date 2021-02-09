package com.kingz.utils;

import java.util.concurrent.ThreadFactory;

public class ThreadUtils {
    public static ThreadFactory threadFactory(final String name, final boolean daemon) {
        return runnable -> {
            Thread result = new Thread(runnable, name);
            result.setDaemon(daemon);
            return result;
        };
    }
}
